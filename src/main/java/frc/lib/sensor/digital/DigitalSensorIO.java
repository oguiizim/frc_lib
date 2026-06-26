package frc.lib.sensor.digital;

/**
 * Sensor digital base.
 * <p>
 * Estes sensores trabalham com valores digitais como verdadeiro ou falso.
 * 
 * <h3>Lembre-se de inverter a resposta do sensor</h3>
 * <p>
 */
public interface DigitalSensorIO {

    /*
     * Retorna se o sensor captou algo de acordo com o sentido de resposta.
     */
    boolean isTriggered();

    /*
     * Retorna o nome atribuído ao sensor.
     */
    String getName();
}
