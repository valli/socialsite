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

package com.socialsite.persistence;

public class InfoMsg extends Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	/** The sender who sends the friend request */
	private User Sender;

	public InfoMsg()
	{
	}

	public String getMessage()
	{
		return message;
	}

	public User getSender()
	{
		return Sender;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setSender(final User sender)
	{
		Sender = sender;
	}

}
