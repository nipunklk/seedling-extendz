/**
 *  @author Randika Hapugoda
 */
export class AccessTokenResponse {
  access_token?: string;
  username?: string;
  expires_in?: number;
  refresh_expires_in?: number;
  refresh_token?: string;
  token_type?: string;
  not_before_policy?: number;
  session_state?: string;
  scope?: string;
}
