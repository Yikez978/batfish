package org.batfish.representation.cisco;

import java.io.Serializable;
import java.util.List;
import org.batfish.common.Warnings;
import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.routing_policy.statement.Statement;

public abstract class RoutePolicyStatement implements Serializable {

   private static final long serialVersionUID = 1L;

   public abstract void applyTo(
         List<Statement> statements,
         CiscoConfiguration cc, Configuration c, Warnings w);

}
