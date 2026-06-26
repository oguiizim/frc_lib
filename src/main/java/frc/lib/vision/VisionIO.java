package frc.lib.vision;

import java.util.List;
import java.util.Optional;

import org.dyn4j.geometry.Rotation;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.util.LEDModes;
import frc.lib.vision.types.VisionPoseEstimate;
import frc.lib.vision.types.VisionTarget;
import swervelib.SwerveDrive;

public interface VisionIO {

    /**
     * Indica se a câmera está detectando pelo menos um alvo válido no momento.
     *
     * @return true se houver ao menos um target detectado, caso contrário false.
     */
    boolean hasTarget();

    /**
     * Retorna o identificador/nome da câmera utilizada no sistema de visão.
     *
     * @return nome da câmera.
     */
    String getName();

    /**
     * Retorna todos os alvos atualmente detectados pela câmera.
     * A lista pode estar vazia caso nenhum alvo seja encontrado.
     *
     * @return lista de VisionTarget detectados.
     */
    List<VisionTarget> getTargets();

    /**
     * Retorna o alvo considerado como o "melhor" entre os detectados,
     * baseado em algum critério de qualidade (ex: área, ambiguidade, distância).
     *
     * @return Optional contendo o melhor VisionTarget, ou vazio se não houver
     *         alvos.
     */
    Optional<VisionTarget> getBestTarget();

    /**
     * Define o pipeline ativo da câmera de visão.
     * Pipelines são configurações diferentes de processamento de imagem
     * usadas para diferentes tarefas (ex: AprilTags, driver cam, etc).
     *
     * @param pipeline índice do pipeline a ser ativado.
     */
    void setPipeline(int pipeline);

    /**
     * Controla o modo de operação dos LEDs da câmera.
     * Pode ser usado para melhorar a detecção ou economizar energia.
     *
     * @param mode modo desejado de operação dos LEDs.
     */
    void setLEDMode(LEDModes mode);

    /**
     * Define a orientação da câmera em relação ao robô.
     * Esses valores descrevem a rotação da câmera no espaço do robô:
     * yaw (rotação horizontal), pitch (inclinação vertical) e roll (inclinação
     * lateral).
     *
     * Essa informação é usada para corrigir a geometria da visão e melhorar
     * a precisão da estimativa de pose.
     *
     * @param yaw   rotação horizontal em graus
     * @param pitch inclinação vertical em graus
     * @param roll  inclinação lateral em graus
     */
    void setRobotOrientation(double yaw, double pitch, double roll);

    /**
     * Define a posição física da câmera no robô.
     * Essa configuração descreve a translação e rotação da câmera
     * em relação ao centro do robô.
     *
     * Importante: esses valores devem corresponder à montagem real,
     * caso contrário a estimativa de pose pode ficar incorreta.
     *
     * @param forward distância para frente a partir do centro do robô (metros)
     * @param side    distância lateral a partir do centro do robô (metros)
     * @param up      altura em relação ao centro do robô (metros)
     * @param roll    rotação lateral da câmera (graus)
     * @param pitch   inclinação vertical da câmera (graus)
     * @param yaw     rotação horizontal da câmera (yaw, em graus)
     */
    void setCameraPose(double forward, double side, double up,
            double roll, double pitch, double yaw);

    /**
     * Retorna a melhor estimativa de pose do robô calculada pela câmera de visão.
     * Essa estimativa utiliza o sistema de visão (ex: MegaTag2) para determinar a
     * posição
     * do robô no campo com base nas AprilTags detectadas e na orientação do robô.
     *
     * O resultado pode incluir informações como posição do robô, timestamp da
     * captura
     * e qualidade da detecção (dependendo da implementação).
     * 
     * @param yaw rotação 2d do robô
     *
     * @return um Optional contendo a estimativa de pose caso exista uma leitura
     *         válida,
     *         ou Optional.empty() caso não haja detecção confiável no momento.
     */
    Optional<VisionPoseEstimate> getPoseEstimate(Rotation2d yaw);
}