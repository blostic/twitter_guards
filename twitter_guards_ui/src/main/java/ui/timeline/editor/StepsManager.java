package ui.timeline.editor;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class StepsManager extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private Button nextButton = new Button("NEXT");
	private Button previousButton = new Button("PREVIOUS");
	private Button finishButton = new Button("FINISH");

	public StepsManager() {
		showStartArrangement();
		setWidth("550px");
		
		getNextButton().addStyleName("no-default-style");
		getNextButton().addStyleName("good-looking-button");
		getNextButton().addStyleName("editor-next-button");
		nextButton.setId("campaign-next-button");
		
		getPreviousButton().addStyleName("no-default-style");
		getPreviousButton().addStyleName("good-looking-button");
		getPreviousButton().addStyleName("editor-previous-button");
		
		getFinishButton().addStyleName("no-default-style");
		getFinishButton().addStyleName("good-looking-button");
		getFinishButton().addStyleName("editor-finish-button");
		finishButton.setId("campaign-finish-button");
		
	}

	public void showStartArrangement() {
		removeAllComponents();
		addComponent(getNextButton());
		setComponentAlignment(getNextButton(), Alignment.MIDDLE_RIGHT);
	}

	public void showCommonArrangement() {
		removeAllComponents();
		addComponents(getPreviousButton(), getNextButton());
		setComponentAlignment(getPreviousButton(), Alignment.MIDDLE_LEFT);
		setComponentAlignment(getNextButton(), Alignment.MIDDLE_RIGHT);
	}

	public void showFinalArrangement() {
		removeAllComponents();
		addComponents(getPreviousButton(), getFinishButton());
		setComponentAlignment(getPreviousButton(), Alignment.MIDDLE_LEFT);
		setComponentAlignment(getFinishButton(), Alignment.MIDDLE_RIGHT);		
	}

	public Button getNextButton() {
		return nextButton;
	}

	public void setNextButton(Button nextButton) {
		this.nextButton = nextButton;
	}

	public Button getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(Button previousButton) {
		this.previousButton = previousButton;
	}

	public Button getFinishButton() {
		return finishButton;
	}

	public void setFinishButton(Button finishButton) {
		this.finishButton = finishButton;
	}

}
