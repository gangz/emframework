package emframework.util.json;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@JsonIgnoreProperties(ignoreUnknown = true)
class MiniProgramInfo {
	MiniProgramInfo(){}
	private int visit_status;
	public int getVisit_status() {
		return visit_status;
	}
	public void setVisit_status(int visit_status) {
		this.visit_status = visit_status;
	}
	@Override
	public String toString() {
		return "MiniProgramInfo [visit_status=" + visit_status + "]";
	}
}


@JsonIgnoreProperties(ignoreUnknown = true)
class AuthorizationInfo {
	String authorizer_appid;

	@Override
	public String toString() {
		return "AuthorizationInfo [authorizer_appid=" + authorizer_appid + "]";
	}
}


@JsonIgnoreProperties(ignoreUnknown = true)
class AuthorizerInfo {
	AuthorizerInfo(){};
	private String qrcode_url;
	@Override
	public String toString() {
		return "AuthorizerInfo [qrcode_url=" + qrcode_url + ", MiniProgramInfo=" + MiniProgramInfo + "]";
	}
	private MiniProgramInfo MiniProgramInfo;
	public String getQrcode_url() {
		return qrcode_url;
	}
	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
	public MiniProgramInfo getMiniProgramInfo() {
		return MiniProgramInfo;
	}
	public void setMiniProgramInfo(MiniProgramInfo miniProgramInfo) {
		MiniProgramInfo = miniProgramInfo;
	}
}

//@JsonIgnoreProperties(ignoreUnknown = true)
class WxMessage{
	WxMessage(){}
	private AuthorizerInfo authorizer_info;
	public AuthorizerInfo getAuthorizer_info() {
		return authorizer_info;
	}
	public void setAuthorizer_info(AuthorizerInfo authorizer_info) {
		this.authorizer_info = authorizer_info;
	}
	public AuthorizationInfo getAuthorization_info() {
		return authorization_info;
	}
	public void setAuthorization_info(AuthorizationInfo authorization_info) {
		this.authorization_info = authorization_info;
	}
	@Override
	public String toString() {
		return "WxMessage [authorizer_info=" + authorizer_info + ", authorization_info=" + authorization_info + "]";
	}
	AuthorizationInfo authorization_info;
}

public class JsonUtilTest {

	
   String t ="{" + 
		   "  \"authorizer_info\":{" + 
		   "    \"qrcode_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/6ljJhicEadRNKWeS7eguB8JQz8HOHzBe8icVZyKibVAmG4sGmUxCQRTTOngRQSwPYs0uPsTzrV46thicXGQb1n2p1Q/0\"," + 
		   "    \"business_info\":{" + 
		   "      \"open_pay\":0," + 
		   "      \"open_card\":0," + 
		   "      \"open_scan\":0," + 
		   "      \"open_store\":0," + 
		   "      \"open_shake\":0" + 
		   "    }," + 
		   "    \"signature\":\"VMiCloud###\"," + 
		   "    \"user_name\":\"gh_c66de7572fb9\"," + 
		   "    \"nick_name\":\"VMiCloud\"," + 
		   "    \"head_img\":\"http://wx.qlogo.cn/mmopen/NyeiaKV6s3uAkT3q4nhgrr7miaKficqiaBPnTuHMdP4SfBrkAvIAiaDTB3nEXq1IbJuMjWr6iaxsrbSjD8gCrt9UhwbpUJUNuibf2iaR/0\"," + 
		   "    \"principal_name\":\"AAA\"," + 
		   "    \"alias\":\"\"," + 
		   "    \"idc\":1," + 
		   "    \"miniProgramInfo\":{" + 
		   "      \"visit_status\":1000," + 
		   "      \"categories\":[" + 
		   "        " + 
		   "      ]," + 
		   "      \"network\":{" + 
		   "        \"WsRequestDomain\":[" + 
		   "          " + 
		   "        ]," + 
		   "        \"RequestDomain\":[" + 
		   "          \"https://api.vmicloud.com\"," + 
		   "          \"https://tapi.vmicloud.com\"," + 
		   "          \"https://up-z0.qiniu.com\"" + 
		   "        ]," + 
		   "        \"UploadDomain\":[" + 
		   "          \"https://up-z0.qiniu.com\"," + 
		   "          \"https://up-z2.qiniu.com\"," + 
		   "          \"https://up.qbox.me\"," + 
		   "          \"https://up-z2.qbox.me\"" + 
		   "        ]," + 
		   "        \"DownloadDomain\":[" + 
		   "          " + 
		   "        ]" + 
		   "      }" + 
		   "    }," + 
		   "    \"verify_type_info\":{" + 
		   "      \"id\":-1" + 
		   "    }," + 
		   "    \"service_type_info\":{" + 
		   "      \"id\":0" + 
		   "    }" + 
		   "  }," + 
		   "  \"authorization_info\":{" + 
		   "    \"authorizer_appid\":\"wx145c74af964418e5\"," + 
		   "    \"func_info\":[" + 
		   "      {" + 
		   "        \"funcscope_category\":{" + 
		   "          \"id\":17" + 
		   "        }" + 
		   "      }," + 
		   "      {" + 
		   "        \"funcscope_category\":{" + 
		   "          \"id\":18" + 
		   "        }" + 
		   "      }" + 
		   "    ]" + 
		   "  }" + 
		   "}";
	
	@Test
	public void testJsonConverter() {
		IdDTO id = new IdDTO("abc");
		assertEquals("{\"id\":\"abc\"}",JsonUtil.toJsonString(id));
	}

	@Test
	public void fromJsonStringtoObject() throws JsonParseException, JsonMappingException, IOException{
		String json = "{\"id\":\"abc\"}";
		IdDTO id = JsonUtil.parseJson(json, IdDTO.class);
		assertEquals("abc",id.getId());
	}
	
	@Test
	public void testJsonParser() throws JsonParseException, JsonMappingException, IOException{
		WxMessage m = JsonUtil.parseJson(t, WxMessage.class);
		System.out.println(m);
	}
}
