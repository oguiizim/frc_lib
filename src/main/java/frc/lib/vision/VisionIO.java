package frc.lib.vision;

import java.util.List;
import java.util.Optional;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.util.LEDModes;
import frc.lib.vision.types.VisionPoseEstimate;
import frc.lib.vision.types.VisionTarget;

/**
 * Interface responsável por abstrair um sistema de visão utilizado pelo robô.
 *
 * <p>
 * Seu objetivo é fornecer uma API comum para diferentes implementações
 * de câmeras ou bibliotecas de visão (como Limelight, PhotonVision, etc.),
 * permitindo que o restante do código permaneça independente da tecnologia
 * utilizada.
 *
 * <p>
 * Além do acesso aos alvos detectados, esta interface também fornece
 * recursos para configuração da câmera e obtenção de estimativas de pose
 * do robô no campo.
 */
public interface VisionIO {

    /**
     * Indica se a câmera está detectando pelo menos um alvo válido.
     *
     * @return {@code true} caso exista ao menos um alvo detectado;
     *         {@code false} caso contrário.
     */
    boolean hasTarget();

    /**
     * Retorna o nome ou identificador da câmera.
     *
     * @return nome da câmera.
     */
    String getName();

    /**
     * Retorna todos os alvos detectados pela câmera na atualização mais recente.
     *
     * <p>
     * A lista pode estar vazia caso nenhum alvo válido tenha sido encontrado.
     *
     * @return lista contendo os alvos detectados.
     */
    List<VisionTarget> getTargets();

    /**
     * Retorna o alvo considerado de maior qualidade entre os detectados.
     *
     * <p>
     * O critério utilizado depende da implementação, podendo considerar
     * fatores como área da imagem, distância, ambiguidade ou confiança.
     *
     * @return um {@link Optional} contendo o melhor alvo, ou vazio caso nenhum
     *         alvo válido esteja disponível.
     */
    Optional<VisionTarget> getBestTarget();

    /**
     * Define o pipeline de processamento ativo da câmera.
     *
     * <p>
     * Pipelines permitem alternar entre diferentes configurações de visão,
     * como detecção de AprilTags, visão para o motorista ou processamento
     * específico para determinados alvos.
     *
     * @param pipeline índice do pipeline desejado.
     */
    void setPipeline(int pipeline);

    /**
     * Define o modo de operação dos LEDs da câmera.
     *
     * <p>
     * Os LEDs podem permanecer desligados, ligados ou seguir o comportamento
     * padrão definido pelo dispositivo, dependendo da implementação.
     *
     * @param mode modo de operação dos LEDs.
     */
    void setLEDMode(LEDModes mode);

    /**
     * Informa ao sistema de visão a orientação atual do robô.
     *
     * <p>
     * Esses dados são utilizados por alguns algoritmos de estimativa de pose,
     * como o MegaTag2, para aumentar a precisão da localização.
     *
     * @param yaw   rotação do robô em torno do eixo vertical (graus).
     * @param pitch inclinação do robô em torno do eixo lateral (graus).
     * @param roll  rotação do robô em torno do eixo longitudinal (graus).
     */
    void setRobotOrientation(double yaw, double pitch, double roll);

    /**
     * Define a transformação da câmera em relação ao centro do robô.
     *
     * <p>
     * Essa configuração descreve a posição física e a orientação da câmera
     * montada no robô. Valores incorretos podem comprometer a precisão da
     * estimativa de pose.
     *
     * @param forward deslocamento para frente em relação ao centro do robô
     *                (metros).
     * @param side    deslocamento lateral em relação ao centro do robô (metros).
     * @param up      altura da câmera em relação ao centro do robô (metros).
     * @param roll    rotação da câmera em torno do eixo longitudinal (graus).
     * @param pitch   inclinação da câmera em torno do eixo lateral (graus).
     * @param yaw     rotação da câmera em torno do eixo vertical (graus).
     */
    void setCameraPose(
            double forward,
            double side,
            double up,
            double roll,
            double pitch,
            double yaw);

    /**
     * Obtém a estimativa mais recente da pose do robô produzida pelo sistema
     * de visão.
     *
     * <p>
     * A implementação pode utilizar algoritmos como MegaTag2 para estimar
     * a posição do robô no campo a partir das AprilTags detectadas e da
     * orientação informada pelo giroscópio.
     *
     * @param yaw orientação atual do robô.
     *
     * @return um {@link Optional} contendo a estimativa de pose caso exista
     *         uma leitura válida; caso contrário, {@link Optional#empty()}.
     */
    Optional<VisionPoseEstimate> getPoseEstimate(Rotation2d yaw);
}