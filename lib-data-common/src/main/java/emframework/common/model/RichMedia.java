package emframework.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RichMedia {
	@Column(columnDefinition = "TEXT")
	@AcceptNewValue
	private String text = null;
	@AcceptNewValue
	private String mediaUri = null;
	@AcceptNewValue
	@Column(length = 16)
	private String type = null;

	public RichMedia() {
	}

	public RichMedia(String mediaContent, String mediaType, String mediaUri) {
		this.text = mediaContent;
		this.type = mediaType;
		this.mediaUri = mediaUri;
	}

	public String getText() {
		return text;
	}

	public void setText(String msg) {
		this.text = msg;
	}

	public String getMediaUri() {
		return mediaUri;
	}

	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
