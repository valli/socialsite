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

package com.socialsite.profile;

import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.PrivacyModel;
import com.socialsite.util.PrivacyPanel;

/**
 * @author Ananth
 */
public class BasicInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Profile profile;

	public BasicInfoPanel(String id)
	{
		super(id);
		this.profile = getUser().getProfile();
		setOutputMarkupId(true);
		add(new PrivacyPanel("test", new Model<PrivacyModel>(profile.getTest()), "Test"));
	}
}
