/**
 *     Copyright SocialSite (C) 2009
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.socialsite.course.answer;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.course.comment.AddCommentPanel;
import com.socialsite.course.comment.CommentsPanel;
import com.socialsite.persistence.Answer;

/**
 * @author Ananth
 */
public class AnswerPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnswerPanel(final String id, final IModel<Answer> model)
	{
		super(id, model);
		final Answer answer = model.getObject();
		add(new Label("text", answer.getText()).setEscapeModelStrings(false));

		MarkupContainer commentPanel;
		add(commentPanel = new CommentsPanel("comments", model));
		add(new AddCommentPanel("addcomment", model, commentPanel));

	}

}
