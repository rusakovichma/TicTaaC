FROM azul/zulu-openjdk-alpine:14 AS jlink

RUN "$JAVA_HOME/bin/jlink" --compress=2 --module-path /opt/java/openjdk/jmods --add-modules java.base,java.compiler,java.datatransfer,jdk.crypto.ec,java.desktop,java.instrument,java.logging,java.management,java.naming,java.rmi,java.scripting,java.security.sasl,java.sql,java.transaction.xa,java.xml,jdk.unsupported --output /jlinked

FROM azul/zulu-openjdk-alpine:14

ARG VERSION
ARG UID=1000
ARG GID=1000

ENV user=tictaac
ENV JAVA_HOME=/opt/jdk

COPY --from=jlink /jlinked /opt/jdk/

ADD target/tic-taac-${VERSION}-release.zip /

RUN apk update 																					     && \
	apk add unzip																	 		 		 && \
	apk --no-cache add fontconfig ttf-dejavu														 && \
    unzip tic-taac-${VERSION}-release.zip -d /usr/share/                                     		 && \
    rm tic-taac-${VERSION}-release.zip                                                       		 && \
    addgroup -S -g ${GID} ${user} && adduser -S -D -u ${UID} -G ${user} ${user}                      && \
    mkdir /usr/share/tic-taac/data                                                           		 && \
    chown -R ${user}:0 /usr/share/tic-taac                                                   		 && \
    chmod -R g=u /usr/share/tic-taac                                                        		 && \
    mkdir /report                                                                                    && \
    chown -R ${user}:0 /report                                                                       && \
    chmod -R g=u /report																		     && \
	chmod +x /usr/share/tic-taac/bin/tic-taac.sh

### remove any suid sgid - we don't need them
RUN find / -perm +6000 -type f -exec chmod a-s {} \;
USER ${UID}

VOLUME ["/threat-model", "/report"]

WORKDIR /threat-model

CMD ["--help"]
ENTRYPOINT ["/usr/share/tic-taac/bin/tic-taac.sh"]