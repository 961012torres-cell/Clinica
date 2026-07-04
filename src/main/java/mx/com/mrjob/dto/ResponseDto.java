package mx.com.mrjob.dto;

public class ResponseDto {
	
		private Integer code;
		private String message;
		private Object content;
		
		public ResponseDto() {
			super();
		}

		public ResponseDto(Integer code, String message, Object content) {
			super();
			this.code = code;
			this.message = message;
			this.content = content;
		}
		

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Object getContent() {
			return content;
		}

		public void setContent(Object content) {
			this.content = content;
		}

		@Override
		public String toString() {
			return "ResponseDto [code=" + code + ", message=" + message + ", content=" + content + "]";
		
		
		
	}
}
