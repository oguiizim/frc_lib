package frc.lib.motor;

import edu.wpi.first.units.measure.Voltage;
import frc.lib.utils.NeutralMode;

/**
 * Interface base para controladores de motores utilizados pela biblioteca.
 *
 * <p>
 * Todos os métodos desta interface trabalham utilizando unidades padronizadas,
 * independentemente do fabricante do controlador (REV, CTRE, etc.).
 *
 * <h3>Unidades utilizadas</h3>
 * <ul>
 * <li><b>Posição:</b> Rotações do mecanismo.</li>
 * <li><b>Velocidade:</b> Rotações por segundo (RPS)</li>
 * <li><b>Tensão:</b> Volts.</li>
 * </ul>
 *
 * <p>
 * Para que posição e velocidade representem o mecanismo corretamente,
 * configure a relação de transmissão através de
 * {@link #setMechanismRatio(double)}.
 */
public interface MotorIO {

    /**
     * Retorna o nome atribuído ao controlador.
     *
     * @return Nome do motor.
     */
    String getName();

    /**
     * Controla o motor em malha aberta utilizando porcentagem de saída.
     *
     * @param percent Valor entre -1.0 e 1.0.
     */
    void setPercent(double percent);

    /**
     * Move o mecanismo até uma posição utilizando o controlador PID onboard.
     *
     * <p>
     * A posição é informada em rotações do mecanismo.
     *
     * <p>
     * <b>Compatibilidade:</b> Nem todos os controladores possuem suporte a
     * controle de posição onboard.
     *
     * @param position Posição desejada em rotações.
     */
    void setPosition(double position);

    /**
     * Move o mecanismo até uma posição utilizando PID e FeedForward onboard.
     *
     * @param position Posição desejada em rotações.
     * @param ff       FeedForward em volts.
     */
    void setPosition(double position, double ff);

    /**
     * Controla a velocidade do mecanismo utilizando PID onboard.
     *
     * <p>
     * A velocidade é informada em rotações por segundo (RPS).
     *
     * @param velocity Velocidade desejada em RPS.
     */
    void setVelocity(double velocity);

    /**
     * Controla a velocidade do mecanismo utilizando PID e FeedForward onboard.
     *
     * @param velocity Velocidade desejada em RPS.
     * @param ff       FeedForward em volts.
     */
    void setVelocity(double velocity, double ff);

    /**
     * Aplica uma tensão diretamente ao motor.
     *
     * @param voltage Tensão em volts.
     */
    void setVoltage(double voltage);

    /**
     * Aplica uma tensão diretamente ao motor.
     *
     * @param voltage Tensão utilizando o sistema de unidades da WPILib.
     */
    void setVoltage(Voltage voltage);

    /**
     * Retorna a velocidade atual do mecanismo.
     *
     * @return Velocidade em rotações por segundo (RPS).
     */
    double getVelocity();

    /**
     * Retorna a posição atual do mecanismo.
     *
     * @return Posição em rotações.
     */
    double getPosition();

    /**
     * Retorna a tensão atualmente aplicada ao motor.
     *
     * @return Tensão em volts.
     */
    double getVoltage();

    /**
     * Retorna a temperatura do controlador ou do motor.
     *
     * <p>
     * Disponibilidade depende do hardware utilizado.
     *
     * @return Temperatura em graus Celsius.
     */
    double getTemperature();

    /**
     * Retorna a corrente consumida pelo motor.
     *
     * @return Corrente em amperes.
     */
    double getCurrent();

    /**
     * Configura os ganhos do controlador PID onboard.
     *
     * <p>
     * <b>Compatibilidade:</b> Disponível apenas em controladores que
     * possuem controle PID embarcado.
     *
     * @param kP Ganho proporcional.
     * @param kI Ganho integral.
     * @param kD Ganho derivativo.
     */
    void configurePID(
            double kP,
            double kI,
            double kD);

    /**
     * Configura os ganhos do FeedForward onboard.
     *
     * <p>
     * Os ganhos devem estar nas unidades do mecanismo:
     * <ul>
     * <li>kS → Volts</li>
     * <li>kV → Volts por RPS</li>
     * <li>kA → Volts por RPS²</li>
     * <li>kG → Volts</li>
     * </ul>
     *
     * @param kS Ganho estático.
     * @param kV Ganho de velocidade.
     * @param kA Ganho de aceleração.
     * @param kG Ganho gravitacional.
     */
    void configureFeedFoward(
            double kS,
            double kV,
            double kA,
            double kG);

    /**
     * Configura um FeedForward sem compensação gravitacional.
     *
     * @param kS Ganho estático.
     * @param kV Ganho de velocidade.
     * @param kA Ganho de aceleração.
     */
    void configureFeedFoward(
            double kS,
            double kV,
            double kA);

    /**
     * Define se o sentido de rotação do motor será invertido.
     *
     * @param inverted {@code true} para inverter o sentido.
     */
    void setInverted(boolean inverted);

    /**
     * Configura o modo neutro do controlador.
     *
     * @param mode Brake ou Coast.
     */
    void setIdleMode(NeutralMode mode);

    /**
     * Define o limite de corrente do controlador.
     *
     * @param amps Corrente máxima em amperes.
     */
    void setCurrentLimit(int amps);

    /**
     * Configura o tempo de rampa para mudanças de saída.
     *
     * <p>
     * Quanto maior o valor, mais suave será a aceleração.
     *
     * @param seconds Tempo de rampa em segundos.
     */
    void setRampRate(double seconds);

    /**
     * Configura a relação entre o rotor do motor e o mecanismo.
     *
     * <p>
     * A biblioteca utilizará essa relação para converter automaticamente
     * posição e velocidade.
     *
     * <p>
     * Exemplos:
     * <ul>
     * <li>1.0 → Acoplamento direto (1:1)</li>
     * <li>10.0 → Redução 10:1</li>
     * <li>100.0 → Redução 100:1</li>
     * </ul>
     *
     * @param ratio Relação entre rotações do motor e rotações do mecanismo.
     */
    void setMechanismRatio(double ratio);

    /**
     * Aplica todas as configurações pendentes ao controlador.
     *
     * <p>
     * Em alguns controladores este método pode não realizar nenhuma ação,
     * caso as configurações sejam aplicadas imediatamente.
     */
    void applyConfig();

    /**
     * Interrompe imediatamente a saída do motor.
     */
    void stop();

    /**
     * Define manualmente a posição atual do mecanismo.
     *
     * <p>
     * Utilizado para zerar ou sincronizar encoders.
     *
     * @param position Nova posição em rotações.
     */
    void resetPosition(double position);
}