package com.kmetop.demsy.plugins.web;

import java.util.List;

import com.jiongsoft.cocit.entity.ActionEvent;
import com.jiongsoft.cocit.entity.impl.BaseActionPlugin;
import com.jiongsoft.cocit.orm.expr.Expr;
import com.kmetop.demsy.Demsy;
import com.kmetop.demsy.comlib.web.IResearchOption;
import com.kmetop.demsy.comlib.web.IResearchQuestion;
import com.kmetop.demsy.comlib.web.IResearchSubject;
import com.kmetop.demsy.orm.IOrm;

public class LoadResearchSubject extends BaseActionPlugin {

	@Override
	public void loaded(ActionEvent event) {
		IOrm orm = (IOrm) event.getOrm();

		IResearchSubject subject = (IResearchSubject) event.getReturnValue();
		// StringBuffer questionsText = new StringBuffer();

		Class questionType = Demsy.bizEngine.getType(IResearchQuestion.SYS_CODE);
		Class answerType = Demsy.bizEngine.getType(IResearchOption.SYS_CODE);

		List<IResearchQuestion> questions = orm.query(questionType, Expr.eq("subject", subject));
		subject.setQuestions(questions);

		for (IResearchQuestion q : questions) {
			List<IResearchOption> options = orm.query(answerType, Expr.eq("question", q));
			q.setOptions(options);

			// questionsText.append("\n\n").append(q.getName());
			// for (IResearchOption o : options) {
			// questionsText.append("\n").append(o.getName());
			// }
		}

		// if (questionsText.length() > 0) {
		// subject.setQuestionsJson(questionsText.substring(2));
		// }
	}

}
