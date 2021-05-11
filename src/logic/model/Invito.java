package model;

public class Invito {
    private int id;
    private Persona sender;
    private Persona receiver;
    private Torneo torneo;
    private byte isRead;

    public Invito(int id, Persona sender, Persona receiver, Torneo torneo, byte isRead) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.torneo = torneo;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getSender() {
        return sender;
    }

    public void setSender(Persona sender) {
        this.sender = sender;
    }

    public Persona getReceiver() {
        return receiver;
    }

    public void setReceiverId(Persona receiver) {
        this.receiver = receiver;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public byte getIsRead() {
        return isRead;
    }

    public void setIsRead(byte isRead) {
        this.isRead = isRead;
    }
}
