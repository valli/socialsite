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

package com.socialsite.scrap;

import org.apache.wicket.markup.html.IHeaderResponse;

import com.socialsite.BasePage;
import com.socialsite.user.UserInfoPanel;

/**
 * scrap page
 * 
 * @author Ananth
 */
public class ScrapPage extends BasePage
{

	public ScrapPage()
	{

		// user info
		add(new UserInfoPanel("userinfo"));

		// scrap list panel
		ScrapListPanel scrapListPanel;
		add(scrapListPanel = new ScrapListPanel("scraps"));

		// scrap form
		add(new SendScrapPanel("send", scrapListPanel.scrapListContainer));
	}


	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.renderJavascriptReference("js/socialsite/scrap.js");
	}
}
