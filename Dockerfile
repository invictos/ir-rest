FROM quay.io/wildfly/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

COPY deployments/ir-rest-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]