[cite_start]// This simulates a frame processed by the C++ code (a static sample processed frame) [cite: 37]
const DUMMY_PROCESSED_FRAME_BASE64 =
    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAIAAAD5IpzXAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAS0lEQVR42mP4z8DAwMDATYxMDBwYGBgYGOz4/8+A/x8MDBQYGBgYGQyAgZcEBlYGBgYGIwMDw7YGBgYGEyYGBgaGgz4GBgYGrg4GAAA/L+g3C41bXAAAAABJRU5ErkJggg==";

class EdgeViewer {
    private canvas: HTMLCanvasElement;
    private ctx: CanvasRenderingContext2D;

    constructor() {
        this.canvas = document.getElementById('viewerCanvas') as HTMLCanvasElement;
        this.ctx = this.canvas.getContext('2d')!;

        if (this.ctx) {
            this.loadAndDrawFrame();
        } else {
            console.error("Canvas context not supported.");
        }
    }

    private loadAndDrawFrame() {
        const img = new Image();

        // Load the dummy processed frame
        img.src = DUMMY_PROCESSED_FRAME_BASE64;

        img.onload = () => {
            // Draw the image to the canvas
            this.ctx.drawImage(img, 0, 0, this.canvas.width, this.canvas.height);
            console.log("Dummy processed frame drawn successfully.");
        };

        img.onerror = () => {
            console.error("Failed to load dummy frame.");
        };
    }
}

// Start the viewer once the page is loaded
window.onload = () => {
    new EdgeViewer();
};