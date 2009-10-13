package com.socialsite.scrap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.dao.ScrapDao;
import com.socialsite.dataprovider.ScrapDataProvider;
import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class ScrapListPanel extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** spring dao to handle scrap objects */
	@SpringBean(name = "scrapDao")
	private ScrapDao scrapDao;

	/** container for the scraplist **/
	public WebMarkupContainer scrapListContainer;

	public ScrapListPanel(String id) {
		super(id);

		// container
		scrapListContainer = new WebMarkupContainer("container");
		scrapListContainer.setOutputMarkupId(true);
		add(scrapListContainer);

		ScrapDataProvider scrapDataProvider = new ScrapDataProvider(
				SocialSiteSession.get().getUserId());

		DataView<Scrap> scrapDataView = new DataView<Scrap>("scrap",
				scrapDataProvider, 10) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Scrap> item) {
				// model
				Scrap scrap = item.getModelObject();
				User author = scrap.getAuthor();

				// link to the author page
				UserLink userLink;
				item.add(userLink = new UserLink("user",
						new Model<User>(author)));
				userLink.add(new Label("name", author.getUserName()));

				// message
				item.add(new Label("message", scrap.getMessage()));

				// reply link

				item.add(new ReplyPanel("reply", item.getModel()));

				// delete link
				item.add(new AjaxLink<Scrap>("delete", item.getModel()) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						scrapDao.delete(getModelObject());
						target.addComponent(scrapListContainer);
					}

					@Override
					public boolean isVisible() {
						SessionUser sessionUser = SocialSiteSession.get()
								.getSessionUser();
						// allow the owner and the author to delete the scrap
						if (sessionUser.hasRole("OWNER")
								|| sessionUser.getId() == getModelObject()
										.getAuthor().getId()) {
							return true;
						}
						return false;
					}
				});

			}
		};

		scrapListContainer.add(scrapDataView);

		scrapListContainer.add(new AbstractAjaxBehavior() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onRequest() {

			}
			@Override
			public void renderHead(IHeaderResponse response)
			{
				// 	bind the reply link handler while rendering this container
				response.renderOnDomReadyJavascript(" SocialSite.Scrap.Reply.init() ");
			}
		});
	}

}
