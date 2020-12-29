package control;

import model.Block;
import view.BlockDisplay;
import view.BlockDisplay.Moved;

public class BlockPresenter implements Block.Observer {
    
    public static final int SIZE = 100;
    private final Block block;
    private final BlockDisplay blockDisplay;

    public BlockPresenter(Block block, BlockDisplay blockDisplay) {
        this.block = block;
        this.blockDisplay = blockDisplay;
        this.blockDisplay.on(new Moved() {
            @Override
            public void to(int x, int y) {
                block.moveTo(x / 100+1, Block.MAX - y / 100);
            }
        });
        this.block.register(this);
        this.refresh();
    }

    private void refresh() {
        blockDisplay.paintBlock((block.x()-1)*SIZE, (Block.MAX - block.y())*SIZE);
    }

    @Override
    public void changed() {
        refresh();
    }
    
    
}
