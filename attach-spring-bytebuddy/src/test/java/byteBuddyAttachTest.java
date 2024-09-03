import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.VirtualMachine;
import net.bytebuddy.dynamic.ClassFileLocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @link https://github.com/raphw/byte-buddy/blob/master/byte-buddy-agent/src/test/java/net/bytebuddy/agent/VirtualMachineAttachmentTest.java
 */
public class byteBuddyAttachTest {
    private static final String FOO = "foo";
    private File agent;

    @Before
    public void setUp() throws Exception {
        agent = File.createTempFile("testagent", ".jar");
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().putValue(Attributes.Name.MANIFEST_VERSION.toString(), "1.0");
        manifest.getMainAttributes().putValue("Agent-Class", SampleAgent.class.getName());
        JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(agent), manifest);
        try {
            outputStream.putNextEntry(new JarEntry(SampleAgent.class.getName().replace('.', '/') + ".class"));
            outputStream.write(ClassFileLocator.ForClassLoader.read(SampleAgent.class));
            outputStream.closeEntry();
        } finally {
            outputStream.close();
        }
    }

    @After
    public void tearDown() throws Exception {
        SampleAgent.argument = null;
    }

    @Test(timeout = 10000L)
    public void testAttachment() throws Exception {
        assertThat(SampleAgent.argument, nullValue(String.class));
        VirtualMachine virtualMachine = (VirtualMachine) VirtualMachine.Resolver.INSTANCE.run()
                .getMethod("attach", String.class)
                .invoke(null, ByteBuddyAgent.ProcessProvider.ForCurrentVm.INSTANCE.resolve());
        try {
            virtualMachine.loadAgent(agent.getAbsolutePath(), FOO);
        } finally {
            virtualMachine.detach();
        }
        assertThat(SampleAgent.argument, is(FOO));
    }


}
