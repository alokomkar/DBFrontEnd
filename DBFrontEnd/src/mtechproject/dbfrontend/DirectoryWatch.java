package mtechproject.dbfrontend;

import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;

import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

import mtechproject.dbfrontend.MainTextArea.Console;
import javafx.scene.control.TextArea;

/**
 * Example to watch a directory (or tree) for changes to files.
 */

public class DirectoryWatch {

    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final boolean recursive;
    private boolean trace = false;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        
        //System.setOut(new PrintStream(new FileOutputStream("E:/DirMonitorLog.txt")));
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    public DirectoryWatch(Path dir, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;
       // System.setOut(new PrintStream(new FileOutputStream("E:/DirMonitorLog.txt")));

        if (recursive) {
            System.out.format("Scanning %s ...\n", dir);
            registerAll(dir);
            System.out.println("Done.");
        } else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
    }

    
	/**
     * Process all events for keys queued to the watcher
	 * @param console 
	 * @param textArea 
	 * @return 
	 * @return 
	 * @throws FileNotFoundException 
     */
    void processEvents(Console console) throws FileNotFoundException {
    	
    	String eventString = null;
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
               // System.setOut(new PrintStream(new FileOutputStream("E:/Dirwatch.txt")));
                // print out event
                //System.out.format("%s: %s\n", event.kind().name(), child);
               eventString = event.kind().name() +" : "+ child;
                
              //  textArea.appendText(event.kind().name() +" : "+ child+"\n");
                
                for (char c : eventString.toCharArray()) {
                        try {
							console.write(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }
                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
       // return eventString;
    }

    static void usage() {
        System.err.println("usage: java DirectoryWatch [-r] dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
       boolean recursive = true;
        // register directory and process its events
        Path dir = Paths.get("E:/DVFS");
      // new DirectoryWatch(dir, recursive).processEvents();
    }
}

