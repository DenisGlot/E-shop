package com.denisgl.templates;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Method getName() is implying that 
 * in simple instances will be returned 
 * also url of the servlet   
 */
public enum View {
	@ServletUrl
	HOME {
		@Override
		public String getName() {
			return "home";
		}
	},
	@ServletUrl
	CARD {
		@Override
		public String getName() {
			return "card";
		}
	},
	@ServletUrl
	CATEGORY {
		@Override
		public String getName() {
			return "category";
		}
	},
	@ServletUrl
	FEEDBACK {
		@Override
		public String getName() {
			return "feedback";
		}
	},
	@ServletUrl
	LOGIN {
		@Override
		public String getName() {
			return "login";
		}
	},
	@ServletUrl
	PRODUCT {
		@Override
		public String getName() {
			return "product";
		}
	},
	@ServletUrl
	REGISTER {
		@Override
		public String getName() {
			return "register";
		}
	},
	FEEDBACKERROR {
		@Override
		public String getName() {
			return "messages/feedbackError";
		}
	},
	FEEDBACKSUCCESS {
		@Override
		public String getName() {
			return "messages/feedbackSuccess";
		}
	},
	PRODUCTERROR{
		@Override
		public String getName() {
			return "messages/productError";
		}
	},
	NONFORWARD {
		@Override
		public String getName() {
			return "";
		}
	};
	public abstract String getName();
	
	private AtomicBoolean redirect = new AtomicBoolean(false);
	
	public boolean isRedirect() {
		return redirect.get();
	}
	public void setRedirect(boolean val) {
		redirect.set(val);
	}
	/**
	 * Request will not forward.
	 * But will redirect to this View.
	 */
	public View redirect() {
	  	redirect.set(true);
	  	return this;
	}
}
