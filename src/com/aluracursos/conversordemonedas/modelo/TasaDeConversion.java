package com.aluracursos.conversordemonedas.modelo;

public class TasaDeConversion {

    private  String time_last_update_utc;

    private String base_code;

    private String target_code;

    private Double conversion_rate;

    private Double conversion_result;

    public TasaDeConversion(TasaDeConversionApi tasaDeConversionApi){
        this.time_last_update_utc = tasaDeConversionApi.time_last_update_utc();
        this.base_code = tasaDeConversionApi.base_code();
        this.target_code = tasaDeConversionApi.target_code();
        this.conversion_rate = tasaDeConversionApi.conversion_rate();
        this.conversion_result = tasaDeConversionApi.conversion_result();
    }

    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }

    public String getBase_code() {
        return base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public Double getConversion_result() {
        return conversion_result;
    }

    @Override
    public String toString() {
        return "TasaDeConversion{" +
                " Fecha de la ultima actualizacion:'" + time_last_update_utc + '\'' +
                ", Moneda base:'" + base_code + '\'' +
                ", Moneda destino:'" + target_code + '\'' +
                ", Tasa de conversion: " + conversion_rate +
                ", Resultado de la conversion: $" + conversion_result +
                '}';
    }
}
