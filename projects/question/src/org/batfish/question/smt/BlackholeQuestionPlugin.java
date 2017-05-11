package org.batfish.question.smt;

import org.batfish.common.Answerer;
import org.batfish.common.BatfishException;
import org.batfish.common.plugin.IBatfish;
import org.batfish.datamodel.answers.AnswerElement;
import org.batfish.datamodel.questions.Question;
import org.batfish.question.QuestionPlugin;
import org.codehaus.jettison.json.JSONObject;

import java.util.Iterator;


public class BlackholeQuestionPlugin extends QuestionPlugin {

    public static class BlackholeAnswerer extends Answerer {

        public BlackholeAnswerer(Question question, IBatfish batfish) {
            super(question, batfish);
        }

        @Override
        public AnswerElement answer() {
            BlackholeQuestion q = (BlackholeQuestion) _question;
            return _batfish.smtBlackhole(q.getFailures(), q.getFullModel());
        }
    }

    public static class BlackholeQuestion extends HeaderQuestion {

        public BlackholeQuestion() {}

        @Override
        public String getName() {
            return "smt-blackhole";
        }

        @Override
        public boolean getTraffic() {
            return false;
        }
    }


    @Override
    protected Answerer createAnswerer(Question question, IBatfish batfish) {
        return new BlackholeAnswerer(question, batfish);
    }

    @Override
    protected Question createQuestion() {
        return new BlackholeQuestion();
    }
}