package frc.lib.sensor.digital;

public interface DigitalSensorIO {

    /*
     * Retorna se o sensor captou algo de acordo com o sentido de resposta.
     */
    boolean isTriggered();

    /*
     * Retorna o reultado do sensor em binário.
     */
    int getBinary();

    /*
     * Retorna o nome atribuído ao sensor.
     */
    String getName();
}
