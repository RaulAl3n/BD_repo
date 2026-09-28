import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocacaoTest {

    private static final double DELTA = 0.001;

    // ---------------------------------------------------------------
    // R1 - valor_diarias = dias x valor_diaria
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R1 - Cálculo do valor das diárias")
    class ValorDiariasTest {

        @Test
        @DisplayName("Deve multiplicar dias por valor da diária")
        void deveCalcularValorDiariasCorretamente() {
            Locacao locacao = new Locacao(5, 100.0, 0, 25);
            assertEquals(500.0, locacao.getValorDiarias(), DELTA);
        }

        @Test
        @DisplayName("Deve considerar valor da diária com casas decimais")
        void deveCalcularValorDiariasComCasasDecimais() {
            Locacao locacao = new Locacao(3, 99.90, 0, 25);
            assertEquals(299.70, locacao.getValorDiarias(), DELTA);
        }
    }

    // ---------------------------------------------------------------
    // R2 - Franquia, excedente e adicional
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R2 - Franquia, excedente e adicional de km")
    class FranquiaExcedenteAdicionalTest {

        @Test
        @DisplayName("Franquia deve ser dias x 100km")
        void deveCalcularFranquiaCorretamente() {
            Locacao locacao = new Locacao(4, 100.0, 0, 25);
            assertEquals(400.0, locacao.getFranquia(), DELTA);
        }

        @Test
        @DisplayName("Excedente deve ser zero quando quilometragem for menor que a franquia")
        void deveRetornarExcedenteZeroQuandoQuilometragemMenorQueFranquia() {
            // franquia = 3 * 100 = 300, quilometragem = 200 -> excedente negativo -> 0
            Locacao locacao = new Locacao(3, 100.0, 200, 25);
            assertEquals(0.0, locacao.getExcedente(), DELTA);
            assertEquals(0.0, locacao.getAdicional(), DELTA);
        }

        @Test
        @DisplayName("Excedente deve ser zero quando quilometragem for exatamente igual à franquia")
        void deveRetornarExcedenteZeroQuandoQuilometragemIgualFranquia() {
            Locacao locacao = new Locacao(3, 100.0, 300, 25);
            assertEquals(0.0, locacao.getExcedente(), DELTA);
        }

        @Test
        @DisplayName("Excedente deve ser a diferença quando quilometragem ultrapassar a franquia")
        void deveCalcularExcedenteQuandoQuilometragemMaiorQueFranquia() {
            // franquia = 3 * 100 = 300, quilometragem = 350 -> excedente = 50
            Locacao locacao = new Locacao(3, 100.0, 350, 25);
            assertEquals(50.0, locacao.getExcedente(), DELTA);
        }

        @Test
        @DisplayName("Adicional deve ser excedente x R$ 0,50")
        void deveCalcularAdicionalCorretamente() {
            Locacao locacao = new Locacao(3, 100.0, 350, 25);
            // excedente = 50 -> adicional = 25.0
            assertEquals(25.0, locacao.getAdicional(), DELTA);
        }
    }

    // ---------------------------------------------------------------
    // R4 - Desconto por tempo de locação (limites: 6, 7, 14, 15)
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R4 - Desconto por tempo de locação")
    class DescontoTest {

        @ParameterizedTest(name = "dias={0} -> desconto deve ser 0")
        @CsvSource({"1", "3", "6"})
        @DisplayName("Não deve haver desconto quando dias <= 6")
        void naoDeveHaverDescontoAteSeisDias(int dias) {
            Locacao locacao = new Locacao(dias, 100.0, 0, 25);
            assertEquals(0.0, locacao.getDesconto(), DELTA);
        }

        @ParameterizedTest(name = "dias={0} -> desconto de 5%")
        @CsvSource({"7", "10", "14"})
        @DisplayName("Desconto deve ser 5% quando 7 <= dias <= 14")
        void deveDarDescontoDeCincoPorCentoEntreSeteEQuatorzeDias(int dias) {
            Locacao locacao = new Locacao(dias, 100.0, 0, 25);
            double esperado = locacao.getValorDiarias() * 0.05;
            assertEquals(esperado, locacao.getDesconto(), DELTA);
        }

        @ParameterizedTest(name = "dias={0} -> desconto de 10%")
        @CsvSource({"15", "20", "30"})
        @DisplayName("Desconto deve ser 10% quando dias > 14")
        void deveDarDescontoDeDezPorCentoAcimaDeQuatorzeDias(int dias) {
            Locacao locacao = new Locacao(dias, 100.0, 0, 25);
            double esperado = locacao.getValorDiarias() * 0.10;
            assertEquals(esperado, locacao.getDesconto(), DELTA);
        }

        @Test
        @DisplayName("Desconto não deve incidir sobre o adicional de km (apenas sobre as diárias)")
        void descontoNaoDeveIncidirSobreAdicional() {
            // 20 dias -> desconto 10% ; quilometragem gera adicional
            Locacao locacao = new Locacao(20, 100.0, 2500, 25);
            double valorDiarias = locacao.getValorDiarias(); // 2000
            double descontoEsperado = valorDiarias * 0.10;   // 200
            assertEquals(descontoEsperado, locacao.getDesconto(), DELTA);
            assertTrue(locacao.getAdicional() > 0, "Deveria existir adicional de km neste cenário");
        }
    }

    // ---------------------------------------------------------------
    // R5 - Taxa de idade (limites: 17 inválido, 18, 20, 21)
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R5 - Taxa de idade do motorista")
    class TaxaIdadeTest {

        @ParameterizedTest(name = "idade={0} -> taxa de R$ 150,00")
        @CsvSource({"18", "19", "20"})
        @DisplayName("Deve cobrar taxa de R$ 150,00 quando idade < 21")
        void deveCobrarTaxaQuandoIdadeMenorQueVinteEUm(int idade) {
            Locacao locacao = new Locacao(3, 100.0, 0, idade);
            assertEquals(150.0, locacao.getTaxaIdade(), DELTA);
        }

        @ParameterizedTest(name = "idade={0} -> sem taxa")
        @CsvSource({"21", "30", "60"})
        @DisplayName("Não deve cobrar taxa quando idade >= 21")
        void naoDeveCobrarTaxaQuandoIdadeMaiorOuIgualVinteEUm(int idade) {
            Locacao locacao = new Locacao(3, 100.0, 0, idade);
            assertEquals(0.0, locacao.getTaxaIdade(), DELTA);
        }
    }

    // ---------------------------------------------------------------
    // R6 - Validações / exceções
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R6 - Validações e exceções")
    class ValidacaoTest {

        @ParameterizedTest(name = "dias={0} -> deve lançar exceção")
        @CsvSource({"0", "-1", "-10"})
        @DisplayName("Deve lançar exceção quando dias <= 0")
        void deveLancarExcecaoQuandoDiasMenorOuIgualZero(int dias) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Locacao(dias, 100.0, 0, 25));
        }

        @ParameterizedTest(name = "valorDiaria={0} -> deve lançar exceção")
        @CsvSource({"0.0", "-1.0", "-99.90"})
        @DisplayName("Deve lançar exceção quando valor_diaria <= 0")
        void deveLancarExcecaoQuandoValorDiariaMenorOuIgualZero(double valorDiaria) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Locacao(3, valorDiaria, 0, 25));
        }

        @ParameterizedTest(name = "idade={0} -> deve lançar exceção")
        @CsvSource({"17", "10", "0"})
        @DisplayName("Deve lançar exceção quando idade <= 17")
        void deveLancarExcecaoQuandoIdadeMenorOuIgualDezessete(int idade) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Locacao(3, 100.0, 0, idade));
        }

        @Test
        @DisplayName("Não deve lançar exceção quando idade = 18 (limite válido)")
        void naoDeveLancarExcecaoQuandoIdadeDezoito() {
            assertEquals(18, new Locacao(3, 100.0, 0, 18).getIdadeMotorista());
        }

        @Test
        @DisplayName("Deve lançar exceção quando quilometragem for negativa")
        void deveLancarExcecaoQuandoQuilometragemNegativa() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Locacao(3, 100.0, -1, 25));
        }

        @Test
        @DisplayName("Não deve lançar exceção quando quilometragem = 0")
        void naoDeveLancarExcecaoQuandoQuilometragemZero() {
            assertEquals(0.0, new Locacao(3, 100.0, 0, 25).getQuilometragem(), DELTA);
        }
    }

    // ---------------------------------------------------------------
    // R7 - valor_total = valor_diarias - desconto + adicional + taxa_idade
    // ---------------------------------------------------------------
    @Nested
    @DisplayName("R7 - Valor total da locação")
    class ValorTotalTest {

        @Test
        @DisplayName("Cenário simples: sem desconto, sem excedente, sem taxa de idade")
        void cenarioSimples() {
            // 5 dias, 100/dia, sem km excedente, motorista com 25 anos
            Locacao locacao = new Locacao(5, 100.0, 400, 25);
            // valor_diarias = 500, desconto = 0, adicional = 0, taxa = 0
            assertEquals(500.0, locacao.getValorTotal(), DELTA);
        }

        @Test
        @DisplayName("Cenário completo: com desconto, adicional de km e taxa de idade")
        void cenarioCompleto() {
            // 20 dias, 100/dia, 2500km rodados, motorista com 19 anos
            Locacao locacao = new Locacao(20, 100.0, 2500, 19);

            double valorDiarias = 2000.0;      // 20 * 100
            double franquia = 2000.0;          // 20 * 100km
            double excedente = 500.0;          // 2500 - 2000
            double adicional = 250.0;          // 500 * 0.50
            double desconto = valorDiarias * 0.10; // dias > 14 -> 200.0
            double taxaIdade = 150.0;          // idade < 21

            double esperado = valorDiarias - desconto + adicional + taxaIdade; // 2200.0

            assertEquals(valorDiarias, locacao.getValorDiarias(), DELTA);
            assertEquals(franquia, locacao.getFranquia(), DELTA);
            assertEquals(excedente, locacao.getExcedente(), DELTA);
            assertEquals(adicional, locacao.getAdicional(), DELTA);
            assertEquals(desconto, locacao.getDesconto(), DELTA);
            assertEquals(taxaIdade, locacao.getTaxaIdade(), DELTA);
            assertEquals(esperado, locacao.getValorTotal(), DELTA);
        }

        @Test
        @DisplayName("Motorista maior de idade e locação curta não deve ter desconto nem taxa")
        void cenarioSemDescontoSemTaxa() {
            Locacao locacao = new Locacao(6, 200.0, 0, 21);
            // valor_diarias = 1200, desconto = 0 (dias<=6), taxa = 0 (idade>=21)
            assertEquals(1200.0, locacao.getValorTotal(), DELTA);
        }
    }
}
