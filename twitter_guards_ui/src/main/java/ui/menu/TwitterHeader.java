package ui.menu;

import ui.ContentWrapper;
import ui.resources.TwitterGuardsIcons;
import ui.user.UserManagementView;
import ui.views.CampaignTimelineView;
import ui.views.OverviewView;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class TwitterHeader extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout menu = new HorizontalLayout();
	private ContentWrapper contentWrapper;
	
	public TwitterHeader() {
		setHeight("60px");
		setWidth("100%");
		
		
		addComponent(menu);

		menu.setSpacing(true);
		setComponentAlignment(menu, Alignment.MIDDLE_RIGHT);
		setExpandRatio(menu, 0.5f);
		
		addOvierviewIcon();
		addTimelineIcon();
		addUserIcon();
		addProductIcon();

		setStyleName("twitter-guards-menu");
		setSpacing(true);
	}

	private void addProductIcon() {
		Image twitterIcon = new Image();
		twitterIcon.setSource(TwitterGuardsIcons.MAIN_VIEW_ICON.getFileResource());
		twitterIcon.setStyleName("logo-style");
		twitterIcon.setHeight("110px");
		addComponent(twitterIcon);
		
		setComponentAlignment(twitterIcon, Alignment.MIDDLE_LEFT);
		setExpandRatio(twitterIcon, 0.5f);
	}

	private VerticalLayout getIconVerticalLayout(String styleName) {
		VerticalLayout icon = new VerticalLayout();
		icon.addStyleName("menu-icon");
		icon.addStyleName(styleName);
		icon.setHeight("60px");
		icon.setWidth("60px");
		menu.addComponent(icon);
		menu.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
		return icon;
	}
	
	private void addUserIcon() {
		VerticalLayout icon = getIconVerticalLayout("user-icon");
		icon.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				contentWrapper.setContent(new UserManagementView());
				
			}
		});
	}

	private void addOvierviewIcon() {
		VerticalLayout icon = getIconVerticalLayout("overview-icon");
		icon.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				contentWrapper.setContent(new OverviewView());
				
			}
		});
	}

	private void addTimelineIcon() {
		VerticalLayout icon = getIconVerticalLayout("timeline-icon");
		icon.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				contentWrapper.setContent(new CampaignTimelineView());
				
			}
		});
	}

	public void setContentWrapper(ContentWrapper contentWrapper) {
		this.contentWrapper = contentWrapper;
	}
}
