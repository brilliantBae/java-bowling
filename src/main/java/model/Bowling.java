package model;

import java.util.ArrayList;
import java.util.List;
import Exception.InvalidFrameNumberException;

public class Bowling {
	private List<Frame> frames = new ArrayList<>();
	private static final int FRAME_START_NO = 1;
	public Frame currentFrame = new NormalFrame(FRAME_START_NO);

	private void addFirstFrame() {
		frames.add(currentFrame);
	}

	public void bowl(Pin pin) throws InvalidFrameNumberException {
		if (frames.size() == 0) {
			addFirstFrame();
		}
		Frame frame = currentFrame.addAfterDecide(pin);
		if (currentFrame.isNewFrame(frame)) {// 현재 프레임이 끝났으면,
			frames.add(frame);
			currentFrame = frame;
		}
	}

	public List<String> getScores() {
		List<String> scores = new ArrayList<>();
		for (int i = 0; i < currentFrame.getFrameNum(); i++) {
			String score = frames.get(i).createScore();
			if (score != "") {
				scores.add(score);
			}
		}
		return scores;
	}

	public List<String> getTotal() {
		List<String> totalScores = new ArrayList<>();
		List<String> scores = getScores();
		int total = 0;
		for (int i = 0; i < scores.size(); i++) {
			if (scores.isEmpty() || scores.get(i) == null) {
				totalScores.add("  ");
				return totalScores;
			}
			total += Integer.parseInt(scores.get(i));
			totalScores.add(total + "");
		}
		return totalScores;
	}

	public List<String> makeStatus() {
		List<String> allStatus = new ArrayList<>();
		for (Frame frame : this.frames) {
			if (!frame.getPins().isEmpty()) {
				allStatus.add(frame.decideStatus());
			}
		}
		return allStatus;
	}

	public List<Frame> getFrames() {
		return frames;
	}
}
