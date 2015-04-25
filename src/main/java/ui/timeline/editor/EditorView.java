package ui.timeline.editor;

import java.util.ArrayList;
import java.util.List;

import ui.TwitterGuardUI;
import ui.timeline.TimelineComponent;
import ui.timeline.editor.steps.BasicConfig;
import ui.timeline.editor.steps.InterestedArea;
import ui.timeline.editor.steps.KeywordsSpecification;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class EditorView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private StepsMenu menu;
	private StepsManager stepsManager;
	
	private List<Layout> steps = new ArrayList<Layout>();
	
	private int currentIndex = 0;
	
	public EditorView() {
		menu = new StepsMenu();
		stepsManager = new StepsManager();
		initiateSteps();
		initiateStepsManager();

		showContent();
	}
	
	private void showContent() {
		removeAllComponents();
		addComponents(menu, steps.get(currentIndex), stepsManager);
		
		setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
		setComponentAlignment(steps.get(currentIndex), Alignment.MIDDLE_CENTER);
		setComponentAlignment(stepsManager, Alignment.MIDDLE_CENTER);
		
		setSpacing(true);
	}
	
	private void initiateStepsManager() {
		stepsManager.getNextButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				currentIndex += 1;
				if (currentIndex < steps.size() - 1) {
					stepsManager.showCommonArrangement();
				} else {
					stepsManager.showFinalArrangement();
				}
				showContent();
			}
		});

		stepsManager.getPreviousButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				currentIndex -= 1;
				if (currentIndex != 0) {
					stepsManager.showCommonArrangement();
				} else {
					stepsManager.showStartArrangement();
				}
				showContent();
			}
		});
				
		stepsManager.getFinishButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				showContent();
				((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new TimelineComponent());
			}
		});
		
	}

	private void initiateSteps() {
		steps.add(new BasicConfig());
		steps.add(new InterestedArea());
		steps.add(new KeywordsSpecification());
	}
	
}
