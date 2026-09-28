/**
 * Modelo de domínio "Locação", implementando as regras de negócio
 * extraídas do diagrama (R1 a R7).
 *
 * OBS.: R3 define valor_total = valor_diarias + adicional, mas R7
 * redefine valor_total = valor_diarias - desconto + adicional + taxa_idade.
 * Como R7 é a regra mais completa (inclui desconto e taxa de idade),
 * ela foi adotada como a fórmula final de valor_total. Ver observações
 * de inconsistência enviadas junto com este código.
 */
public class Locacao {

    private static final double VALOR_KM_FRANQUIA = 100.0;   // R2: dias x 100 km
    private static final double VALOR_KM_EXCEDENTE = 0.50;   // R2: excedente x R$ 0,50
    private static final double TAXA_IDADE = 150.0;          // R5
    private static final int IDADE_LIMITE_TAXA = 21;         // R5: idade < 21 paga taxa
    private static final int IDADE_MINIMA_PERMITIDA = 17;    // R6: idade <= 17 -> exceção

    private final int dias;
    private final double valorDiaria;
    private final double quilometragem;
    private final int idadeMotorista;

    public Locacao(int dias, double valorDiaria, double quilometragem, int idadeMotorista) {
        // R6 - validações
        if (dias <= 0) {
            throw new IllegalArgumentException("dias deve ser maior que zero");
        }
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("valorDiaria deve ser maior que zero");
        }
        if (idadeMotorista <= IDADE_MINIMA_PERMITIDA) {
            throw new IllegalArgumentException("idade do motorista deve ser maior que " + IDADE_MINIMA_PERMITIDA);
        }
        if (quilometragem < 0) {
            throw new IllegalArgumentException("quilometragem não pode ser negativa");
        }

        this.dias = dias;
        this.valorDiaria = valorDiaria;
        this.quilometragem = quilometragem;
        this.idadeMotorista = idadeMotorista;
    }

    /** R1 - valor_diarias = dias x valor_diaria */
    public double getValorDiarias() {
        return dias * valorDiaria;
    }

    /** R2 - Franquia = dias x 100 km */
    public double getFranquia() {
        return dias * VALOR_KM_FRANQUIA;
    }

    /** R2 - Excedente = quilometragem - franquia, quando positivo (senão 0) */
    public double getExcedente() {
        double excedente = quilometragem - getFranquia();
        return excedente > 0 ? excedente : 0.0;
    }

    /** R2 - Adicional = excedente x R$ 0,50 */
    public double getAdicional() {
        return getExcedente() * VALOR_KM_EXCEDENTE;
    }

    /**
     * R4 - Desconto incide somente sobre valor_diarias:
     *   0%   se dias <= 6
     *   5%   se 7 <= dias <= 14
     *   10%  se dias > 14
     */
    public double getDesconto() {
        double valorDiarias = getValorDiarias();
        if (dias <= 6) {
            return 0.0;
        } else if (dias <= 14) {
            return valorDiarias * 0.05;
        } else {
            return valorDiarias * 0.10;
        }
    }

    /** R5 - Taxa de idade = 150,00 se idade < 21, senão 0,00 */
    public double getTaxaIdade() {
        return idadeMotorista < IDADE_LIMITE_TAXA ? TAXA_IDADE : 0.0;
    }

    /** R7 - valor_total = valor_diarias - desconto + adicional + taxa_idade */
    public double getValorTotal() {
        return getValorDiarias() - getDesconto() + getAdicional() + getTaxaIdade();
    }

    public int getDias() {
        return dias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public int getIdadeMotorista() {
        return idadeMotorista;
    }
}
