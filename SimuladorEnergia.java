import java.util.Random;

class ConsumoEnergia {
    private String vilaFacens;
    private double consumoEnergiaTotal;
    private double consumoEnergiaMes;
    private double consumoMesAnterior;
    private double consumoEnergiaDia;
    private double consumoDiaAnterior;
    private int diasNoMes;

    public ConsumoEnergia(String vilaFacens) {
        this.vilaFacens = vilaFacens;
        this.consumoEnergiaTotal = 0;
        this.consumoEnergiaMes = 0;
        this.consumoMesAnterior = 0;
        this.consumoEnergiaDia = 0;
        this.consumoDiaAnterior = 0;
        this.diasNoMes = 30;
    }
    public void adicionarConsumo(double consumo) {
        this.consumoEnergiaTotal += consumo;
        this.consumoEnergiaMes += consumo / diasNoMes;
        this.consumoEnergiaDia += consumo;
    }
    public double consumoEnergiaTotal() {
        return consumoEnergiaTotal;
    }
    public double consumoMes() {
        return consumoEnergiaMes;
    }
    public double consumoMesAnterior() { 
        return consumoMesAnterior;
    }
    public double consumoDiario() {
        return consumoEnergiaDia;
    }
    public double consumoDiaAnterior() { 
        return consumoDiaAnterior;
    }
    public String getVilaFacens() {
        return vilaFacens;
    }
    public void atualizarConsumoMesAnterior() {
        consumoMesAnterior = consumoEnergiaMes;
        consumoEnergiaMes = 0;
    }
    public void atualizarConsumoDiarioAnterior() {
        consumoDiaAnterior = consumoEnergiaDia;
        consumoEnergiaDia = 0;
    }
}
public class SimuladorEnergia {
    public static void main(String[] args) {
        ConsumoEnergia vilaFacens = new ConsumoEnergia("Vila Facens");

        Random random = new Random();

        for (int mes = 1; mes <= 12; mes++) {      // loop de mês
            vilaFacens.atualizarConsumoMesAnterior();

            for (int dia = 1; dia <= 30; dia++) { // loop de dia, assumindo 30 dias em cada mês
                vilaFacens.atualizarConsumoDiarioAnterior();

                double consumoEnergiaDia = 0;

                for (int hora = 1; hora <= 24; hora++) {
                    double consumoHora = random.nextDouble() * 12.2; // Consumo aleatório por hora mas não mostra, só mostra a soma total por dia
                    consumoEnergiaDia += consumoHora;
                }
                vilaFacens.adicionarConsumo(consumoEnergiaDia);

                System.out.println("Mês " + mes + ", Dia " + dia + ": Consumo do dia = " + String.format("%.2f", consumoEnergiaDia) + " kWh");


                // diferença em porcentagem do consumo diário

                if (dia > 1) {
                    double consumoAtual = vilaFacens.consumoDiario();
                    double consumoDiaAnterior = vilaFacens.consumoDiaAnterior();
                    double diferenca = ((consumoAtual - consumoDiaAnterior) / consumoDiaAnterior) * 100;
                   
                    if (diferenca > 0) {
                        System.out.println("O consumo de energia está " + String.format("%.2f", Math.abs(diferenca)) + "% acima do dia anterior.");
                    } else if (diferenca < 0) {
                        System.out.println("O consumo de energia está " + String.format("%.2f", Math.abs(diferenca)) + "% abaixo do dia anterior.");
                    } else {
                        System.out.println("O consumo de energia está igual ao dia anterior.");
                    }
                }
            }

            // diferença em porcentagem do consumo mensal

            if (mes > 1) {
                double consumoMensalAtual = vilaFacens.consumoMes();
                double consumoMesAnterior = vilaFacens.consumoMesAnterior();
                double diferencaMensal = ((consumoMensalAtual - consumoMesAnterior) / consumoMesAnterior) * 100;

                                if (diferencaMensal > 0) {
                    System.out.println("O consumo mensal está " + String.format("%.2f", Math.abs(diferencaMensal)) + "% acima do mês anterior.");
                } else if (diferencaMensal < 0) {
                    System.out.println("O consumo mensal está " + String.format("%.2f", Math.abs(diferencaMensal)) + "% abaixo do mês anterior.");
                } else {
                    System.out.println("O consumo mensal está igual ao mês anterior.");
                }
            }
            System.out.println("Consumo de energia da " + vilaFacens.getVilaFacens() + " (Mês " + mes + "): " + String.format("%.2f", vilaFacens.consumoMes()) + " kWh");
        }
         
    }
}
