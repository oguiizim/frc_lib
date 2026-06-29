package frc.lib.sensor.digital;

/**
 * Interface que abstrai um sensor digital.
 *
 * <p>
 * Permite que diferentes tipos de sensores digitais (como sensores de
 * proximidade, sensores de efeito Hall, chaves de fim de curso, entre outros)
 * sejam utilizados através de uma API comum, independente do hardware
 * específico.
 */
public interface DigitalSensorIO {

    /**
     * Indica se o sensor está acionado.
     *
     * <p>
     * O significado de "acionado" depende da implementação e da lógica
     * adotada pelo sensor (ativo em nível alto ou baixo).
     *
     * @return {@code true} se o sensor estiver acionado;
     *         {@code false} caso contrário.
     */
    boolean isTriggered();

    /**
     * Retorna o estado bruto do sensor.
     *
     * <p>
     * O valor retornado corresponde ao nível lógico lido na entrada digital.
     *
     * @return {@code 1} para nível alto ou {@code 0} para nível baixo.
     */
    int getBinary();

    /**
     * Retorna o nome ou identificador do sensor.
     *
     * @return nome atribuído ao sensor.
     */
    String getName();
}