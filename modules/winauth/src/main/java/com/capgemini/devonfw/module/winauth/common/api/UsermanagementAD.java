package com.capgemini.devonfw.module.winauth.common.api;

/**
 * Interface to get a user from its login.
 *
 * @author jmetzler
 */
public interface UsermanagementAD {

  /**
   * @param login The login of the requested user.
   * @return The {@link UserProfile} with the given <code>login</code> or {@code null} if no such object exists.
   */
  UserProfileAD findUserProfileByLogin(String login);

}
