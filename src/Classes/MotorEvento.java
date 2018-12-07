package Classes;

import java.util.ArrayList;
import java.util.List;

public class MotorEvento {

    private List<Evento> Eventos;

    public MotorEvento() {
        this.Eventos = new ArrayList<>();
    }

    public void AdicionarEvento(Evento evento) {
        this.Eventos.add(evento);
        OrdenarEventos();
    }

    public Evento ObterProximoEvento(int tempo) {

        if(this.Eventos.size() > 0) {
            Evento e = this.Eventos.get(0);

            if(e.TempoAgendado <= tempo) {
                this.Eventos.remove(e);
                return e;
            }
            else {
                return new Evento(Evento.VAZIO,0,0);
            }
        }

        return new Evento(Evento.VAZIO,0,0);
    }

    public int EventosNaFila() {
        return this.Eventos.size();
    }

    private void OrdenarEventos() {
        Eventos.sort((o1, o2) -> {

            long ta1 = o1.TempoAgendado;
            long ta2 = o2.TempoAgendado;

            if(ta1 > ta2) {
                return 1;
            }
            else if (ta1 < ta2) {
                return -1;
            }
            else {
                long to1 = o1.TempoDeOrigem;
                long to2 = o2.TempoDeOrigem;

                if(to1 > to2) {
                    return 1;
                }
                else if (to1 < to2) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
    }

}
