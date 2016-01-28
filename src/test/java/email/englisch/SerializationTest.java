package email.englisch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class SerializationTest {

    @Test
    public void test_that_matchday_is_correctly_serialized() {
        final Matchday serializedMatchday = Matchday.on(LocalDate.now());
        final ByteArrayOutputStream byteArrayOutputStream = serializeMatchday(serializedMatchday);

        Matchday deserializedMatchday = deserializeMatchday(byteArrayOutputStream);

        assertThat(serializedMatchday, is(equalTo(deserializedMatchday)));
    }

    private ByteArrayOutputStream serializeMatchday(Matchday serializedMatchday) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(serializedMatchday);
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return byteArrayOutputStream;
    }

    private Matchday deserializeMatchday(ByteArrayOutputStream byteArrayOutputStream) {
        Matchday deserializedMatchday;
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))) {
            deserializedMatchday = (Matchday) objectInputStream.readObject();
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return deserializedMatchday;
    }
}
