package uma.roadfighter.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

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


    //FIXME: Nestor's legacy
    //DO NOT TOUCH

    /*	@Override
    public boolean onTouchEvent( MotionEvent e ) {
		float x = e.getX();
		float y = e.getY();
		
		switch( e.getAction() ) {
		case MotionEvent.ACTION_DOWN:
			if( ( y > 3 * getHeight() / 4 ) && ( x < 1 * getWidth() / 4 ) )
				renderer.event = 'l';
			if( ( y > 3 * getHeight() / 4 ) && ( x > 3 * getWidth() / 4 ) )
				renderer.event = 'r';
			else if( y < getHeight() / 2 )
				renderer.event = 'n';
			break;
		case MotionEvent.ACTION_UP:
			if( ( y > 3 * getHeight() / 4 ) && ( x < 2 * getWidth() / 4 ) && ( x > 1 * getWidth() / 4 ) )
				renderer.event = 'a';
			else if( ( y > 3 * getHeight() / 4 ) && ( x > 2 * getWidth() / 4 ) && ( x < 3 * getWidth() / 4 ) )
				renderer.event = 'h';
			else
				renderer.event = 'c';
			break;
		}
		
		return true;
	}*/

}
