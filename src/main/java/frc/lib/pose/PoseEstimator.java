package frc.lib.pose;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Interface responsável por abstrair um estimador de pose do robô.
 *
 * <p>
 * Seu objetivo é permitir que diferentes implementações de odometria
 * (como YAGSL, WPILib ou implementações customizadas) sejam utilizadas
 * sem alterar o restante do código da aplicação.
 *
 * <p>
 * Além de fornecer informações sobre a orientação atual do robô,
 * esta interface também define métodos para integrar medições de visão
 * ao estimador de pose.
 */
public interface PoseEstimator {

    /**
     * Retorna a orientação (yaw) atual do robô.
     *
     * @return rotação atual do robô no campo.
     */
    Rotation2d getYaw();

    /**
     * Retorna a velocidade angular atual do robô.
     *
     * <p>
     * Esse valor normalmente é obtido do giroscópio e pode ser utilizado
     * para validar ou rejeitar medições de visão durante rotações rápidas.
     *
     * @return velocidade angular em graus por segundo.
     */
    double getAngularVelocityDegPerSec();

    /**
     * Define o desvio padrão esperado das medições de visão.
     *
     * <p>
     * Esses valores representam a confiança que o estimador deve atribuir
     * às medições recebidas do sistema de visão. Quanto menor o desvio padrão,
     * maior será a influência da visão sobre a pose estimada.
     *
     * @param x     desvio padrão da posição no eixo X (metros).
     * @param y     desvio padrão da posição no eixo Y (metros).
     * @param theta desvio padrão da orientação (radianos).
     */
    void setVisionStdDevs(double x, double y, double theta);

    /**
     * Adiciona uma nova medição de visão ao estimador de pose.
     *
     * <p>
     * O timestamp deve corresponder ao instante em que a imagem foi capturada,
     * e não ao momento em que ela foi processada. Isso permite que o estimador
     * compense corretamente a latência da câmera.
     *
     * @param pose      pose estimada pelo sistema de visão.
     * @param timestamp instante da captura da imagem, em segundos.
     */
    void addVisionMeasurement(Pose2d pose, double timestamp);
}