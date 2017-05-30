
http://stackoverflow.com/questions/137212/how-to-solve-performance-problem-with-java-securerandom

http://security.stackexchange.com/questions/14386/what-do-i-need-to-configure-to-make-sure-my-software-uses-dev-urandom


down vote
General advice
Any program written in Java
Add

-Djava.security.egd=file:///dev/urandom switch
or

-Djava.security.egd=file:/dev/./urandom
to the command line invocation used to start the Java process. (Without this, Java uses /dev/random to seed its SecureRandom class, which can cause Java code to block unexpectedly.)

Alternatively, in the $JAVA_HOME/jre/lib/security/java.security configuration file, add the line

securerandom.source=file:/dev/./urandom
Footnote: In the above examples, you need the crazy-looking filename, e.g., the extra /./, to trick Java into accepting your filename. If you just use /dev/urandom, Java decides you didn't really mean it and replaces what you wrote with /dev/random. Craziness!

Chroot
If you are starting some service in a chroot environment, don't forget to create the /dev/urandom device inside your chroot directory.

Specific software
Apache mod_ssl
Use

SSLRandomSeed startup file:/dev/urandom 512
SSLRandomSeed connect file:/dev/urandom 512
in the mod_ssl configuration file. Avoid using file:/dev/random with SSLRandomSeed.

Cyrus POP3, IMAPD, and SASL
Compile Cyrus SASL (libsasl) with the configuration flag  --with-devrandom=/dev/urandom.

By default, Cyrus POP3 reads from /dev/random. I couldn't find any configuration setting to change this, short of recompiling.

OpenLDAP
Add

TLSRandFile /dev/urandom
to the slapd.conf configuration file. (This hopefully should be the default, but some guides misleadingly suggest using /dev/random, so you might want to double-check.)

Postfix
Use

tls_random_source = dev:/dev/random
in the main.cf configuration file, or

sudo postconf -e 'tls_random_source = dev:/dev/urandom'
from the command line.