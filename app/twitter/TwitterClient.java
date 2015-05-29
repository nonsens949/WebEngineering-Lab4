package twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient implements ITwitterClient{
	
	@Override
	public void publishUuid(TwitterStatusMessage message) throws Exception {
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("GZ6tiy1XyB9W0P4xEJudQ");
		builder.setOAuthConsumerSecret("gaJDlW0vf7en46JwHAOkZsTHvtAiZ3QUd2mD1x26J9w");
		
		Configuration config = builder.build();
		
		TwitterFactory factory = new TwitterFactory(config);
		Twitter twitter = factory.getInstance();
		
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = new AccessToken("1366513208-MutXEbBMAVOwrbFmZtj1r4Ih2vcoHGHE2207002", "RMPWOePlus3xtURWRVnv1TgrjTyK7Zk33evp4KKyA");
		
		twitter.setOAuthAccessToken(accessToken);
		
		Status status = twitter.updateStatus(message.getTwitterPublicationString());
		
	}
}
