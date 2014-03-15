package test.aionemu.commons.network;

import com.aionemu.commons.network.packet.BaseClientPacket;

import java.nio.ByteBuffer;
import org.apache.log4j.Logger;

public abstract class AionClientPacket extends BaseClientPacket<AionConnection> {

    private static final Logger log = Logger.getLogger(AionClientPacket.class);

    protected AionClientPacket(ByteBuffer buf, AionConnection client, int opcode) {
        super(buf, opcode);
        this.setConnection(client);
    }

    @Override
    public final void run() {
        try {
            this.runImpl();
        } catch (Throwable e) {
            log.error("error handling client opcode = {} message" + getConnection().getIP());
        }
    }

    protected void sendPacket(AionServerPacket msg) {
        this.getConnection().sendPacket(msg);
    }
}