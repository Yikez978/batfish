package org.batfish.common.plugin;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import org.batfish.common.Answerer;
import org.batfish.datamodel.Configuration;
import org.batfish.datamodel.DataPlane;
import org.batfish.datamodel.Flow;
import org.batfish.datamodel.FlowHistory;
import org.batfish.datamodel.ForwardingAction;
import org.batfish.datamodel.HeaderSpace;
import org.batfish.datamodel.Ip;
import org.batfish.datamodel.Topology;
import org.batfish.datamodel.answers.AnswerElement;
import org.batfish.datamodel.answers.ConvertConfigurationAnswerElement;
import org.batfish.datamodel.assertion.AssertionAst;
import org.batfish.datamodel.collections.AdvertisementSet;
import org.batfish.datamodel.collections.InterfaceSet;
import org.batfish.datamodel.collections.NamedStructureEquivalenceSets;
import org.batfish.datamodel.collections.NodeInterfacePair;
import org.batfish.datamodel.collections.NodeSet;
import org.batfish.datamodel.questions.Question;

public interface IBatfish extends IPluginConsumer {

   AnswerElement answerAclReachability(String aclNameRegexStr,
         NamedStructureEquivalenceSets<?> aclEqSets);

   void checkConfigurations();

   void checkDataPlane();

   void checkDataPlaneQuestionDependencies();

   void checkEnvironmentExists();

   InterfaceSet computeFlowSinks(Map<String, Configuration> configurations,
         boolean differentialContext, Topology topology);

   Map<Ip, Set<String>> computeIpOwners(
         Map<String, Configuration> configurations);

   Topology computeTopology(Map<String, Configuration> configurations);

   AnswerElement createEnvironment(String environmentName,
         NodeSet nodeBlacklist, Set<NodeInterfacePair> interfaceBlacklist,
         boolean dp);


   AnswerElement smtForwarding(String destination);

   AnswerElement smtReachability(Pattern node1Regex, Pattern ifaceRegex, Pattern node2Regex);

   AnswerElement smtBlackhole();

   AnswerElement smtRoutingLoop();

   AnswerElement smtBoundedLength(Pattern node1Regex, Pattern ifaceRegex, Pattern node2Regex, Integer bound);

   AnswerElement smtEqualLength(Pattern node1Regex, Pattern ifaceRegex, Pattern node2Regex);

   AnswerElement smtMultipathConsistency(Pattern node1Regex, Pattern ifaceRegex);

   AnswerElement smtLoadBalance(Pattern node1Regex, Pattern ifaceRegex, Pattern node2Regex, Pattern peerRegex, int threshold);

   AnswerElement smtLocalConsistency(Pattern routerRegex);


   Map<String, BiFunction<Question, IBatfish, Answerer>> getAnswererCreators();

   ConvertConfigurationAnswerElement getConvertConfigurationAnswerElement();

   String getDifferentialFlowTag();

   String getFlowTag();

   FlowHistory getHistory();

   void initBgpAdvertisements(Map<String, Configuration> configurations);

   void initBgpOriginationSpaceExplicit(
         Map<String, Configuration> configurations);

   void initRemoteBgpNeighbors(Map<String, Configuration> configurations,
         Map<Ip, Set<String>> ipOwners);

   void initRemoteIpsecVpns(Map<String, Configuration> configurations);

   void initRoutes(Map<String, Configuration> configurations);

   Map<String, Configuration> loadConfigurations();

   DataPlane loadDataPlane();

   AnswerElement multipath(HeaderSpace headerSpace);

   AssertionAst parseAssertion(String text);

   AnswerElement pathDiff(HeaderSpace headerSpace);

   void popEnvironment();

   void printElapsedTime();

   AdvertisementSet processExternalBgpAnnouncements(
         Map<String, Configuration> configurations);

   void processFlows(Set<Flow> flows);

   void pushBaseEnvironment();

   void pushDeltaEnvironment();

   AnswerElement reducedReachability(HeaderSpace headerSpace);

   void registerAnswerer(String questionClassName,
         BiFunction<Question, IBatfish, Answerer> answererCreator);

   void resetTimer();

   void setDataPlanePlugin(DataPlanePlugin dataPlanePlugin);

   AnswerElement standard(HeaderSpace headerSpace,
         Set<ForwardingAction> actions, String ingressNodeRegexStr,
         String notIngressNodeRegexStr, String finalNodeRegexStr,
         String notFinalNodeRegexStr);

   void writeDataPlane(DataPlane dp);

}
