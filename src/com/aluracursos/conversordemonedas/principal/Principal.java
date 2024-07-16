package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.service.ConsumoApi;
import com.aluracursos.conversordemonedas.service.ConvierteDatos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        Scanner teclado = new Scanner(System.in);

        String menu = "********************************************\n"
                + "Sea bienvenido/a al Conversor de Monedas =]\n"
                + "\n"
                + "1) Dólar =>> Peso argentino\n"
                + "2) Peso argentino =>> Dólar\n"
                + "3) Dólar =>> Real brasileño\n"
                + "4) Real brasileño =>> Dólar\n"
                + "5) Dólar =>> Peso colombiano\n"
                + "6) Peso colombiano =>> Dólar\n"
                + "7) Salir\n"
                + "Elija una opcion valida\n"
                + "**********************************************";
        String solicitudAConvertir = "Ingresa el valor que deseas convertir";

        while (true){

            try {
                System.out.println(menu);
                var solicitudUsuario = teclado.nextInt();
                //System.out.println(solicitudUsuario);

                if (solicitudUsuario == 7) {
                    break;
                }if (solicitudUsuario <= 0){
                    System.out.println("Opción Invalida, ejecutar nuevamente por favor.");
                    break;
                }if (solicitudUsuario >= 8){
                    System.out.println("Opcion Invalida, ejecutar nuevamente por favor.");
                    break;
                }

                System.out.println(solicitudAConvertir);
                var montoUsuario = teclado.nextDouble();
                //System.out.println(montoUsuario);

                String urlBase = "https://v6.exchangerate-api.com/v6/";
                String apiKey = "c426f4ba47565aaa2c5e2e42";
                String urlRespuesta = "/pair/";
                String monedaBase = "";
                String monedaDestino = "";

                switch (solicitudUsuario) {
                    case 1:
                        monedaBase = "USD";
                        monedaDestino = "ARS";
                        break;
                    case 2:
                        monedaBase = "ARS";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaBase = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaBase = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 5:
                        monedaBase = "USD";
                        monedaDestino = "COP";
                        break;
                    case 6:
                        monedaBase = "COP";
                        monedaDestino = "USD";
                        break;
                }

                URI direccion = URI.create(urlBase + apiKey + urlRespuesta + monedaBase + "/" + monedaDestino + "/" + montoUsuario);

                String json = consumoApi.obtenerDatosApi(direccion);
                //System.out.println("Json: " + json);

                var conversion = conversor.convierteDatos(json);

                BigDecimal resultado = BigDecimal.valueOf(montoUsuario * conversion.conversion_rate());
                System.out.println("El valor de: $" + montoUsuario + " ["
                        + monedaBase + "] corresponde al valor final de =>>> $"
                        + resultado.setScale(2, RoundingMode.HALF_UP) + " [" + monedaDestino + "].");

                var comprobacionResultado = conversion.conversion_result();
                //System.out.println("Comprobacion del valor de: $"+montoUsuario+" ["+monedaBase+"] corresponde al valor final de =>>> $"+comprobacionResultado+" ["+monedaDestino+"].");

            } catch (InputMismatchException e) {
                System.out.println("Acepta solo valores numericos, ejecuta de nuevo por favor");
                break;
            }
        }
    }
}
