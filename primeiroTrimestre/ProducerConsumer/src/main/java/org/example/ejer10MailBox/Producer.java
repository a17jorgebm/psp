package org.example.ejer10MailBox;

import java.util.Random;

public class Producer implements Runnable{
    private static final Random random=new Random();
    private static final String[] EMAILS = {
            "Hola, ¿cómo estás?",
            "Reunión confirmada para mañana.",
            "No olvides adjuntar el archivo.",
            "¿Podemos cambiar la hora del encuentro?",
            "¡Felices fiestas a todos!",
            "Por favor, revisa el informe adjunto.",
            "¿Cuál es el status del proyecto?",
            "Tu pedido ha sido enviado.",
            "Gracias por tu respuesta rápida.",
            "¿Tienes un momento para charlar?",
            "Actualización importante sobre el contrato.",
            "El evento se pospuso hasta nuevo aviso.",
            "Por favor, confirma tu asistencia.",
            "Aquí está el enlace que pediste.",
            "La factura ya ha sido procesada.",
            "Revisé el documento y está todo bien.",
            "No puedo asistir a la reunión de hoy.",
            "¡Buen trabajo en el proyecto!",
            "Por favor, responde antes del viernes.",
            "Avísame si necesitas más información."
    };

    private MailBox mailBox;

    public Producer(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public void run() {
        while (true){
            try{
                String textoEscrito=EMAILS[random.nextInt(0, EMAILS.length)];
                mailBox.putMessage(textoEscrito);
                System.out.printf("\nThread productor %s escribe %s",Thread.currentThread().getName(),textoEscrito);
                Thread.sleep(random.nextInt(0,1001));
            }catch (InterruptedException e){
                System.out.println("Liadote no thread productor "+Thread.currentThread().getName());
            }
        }
    }

}
