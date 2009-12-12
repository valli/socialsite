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

package com.socialsite.search;

import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePage;
import com.socialsite.dataprovider.SearchDataProvider;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.persistence.User;
import com.socialsite.user.UserInfoPanel;

/**
 * search page
 * 
 * @author Ananth
 */
public class SearchPage extends BasePage
{

	/** search filter **/
	private final StringWrapper filter;

	/**
	 * constructor
	 * 
	 * @param searchText
	 *            search text
	 */
	public SearchPage(final StringWrapper filter)
	{
		// intialize the filter
		this.filter = filter;

		// add the user info panel
		add(new UserInfoPanel("userinfo"));

		final SearchDataProvider searchDataProvider = new SearchDataProvider(this.filter);

		final DataView<User> searchList = new DataView<User>("searchlist", searchDataProvider, 10)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<User> item)
			{
				item.add(new SearchUserInfoPanel("userdetails", item.getModel()));
			}
		};

		add(new PagingNavigator("searchpagination", searchList));
		add(searchList);
	}
}
