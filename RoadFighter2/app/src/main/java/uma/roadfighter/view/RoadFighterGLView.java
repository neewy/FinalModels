package uma.roadfighter.view;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class RoadFighterGLView extends GLSurfaceView implements CarHandler {
    RoadFighterGLRenderer renderer;

    RoadFighterGLView(Context context) {
        super(context);

        // Renderer
        renderer = new RoadFighterGLRenderer(context);
        setRenderer(renderer);
    }

    @Override
    public void onLeftPress() {
        renderer.setEvent('l');
    }

    @Override
    public void onRightPress() {
        renderer.setEvent('r');
    }

    @Override
    public void onFastPress() {
        renderer.setEvent('h');
    }

    @Override
    public void onSlowPress() {
        renderer.setEvent('a');
    }

    @Override
    public void onBreakPress() {
        renderer.setEvent('n');
    }

    @Override
    public void onRelease() {
        renderer.setEvent('c');
    }

}
