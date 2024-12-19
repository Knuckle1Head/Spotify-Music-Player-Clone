import java.util.Scanner;

class Song {
    String title;
    Song next, prev;

    public Song(String title) {
        this.title = title;
    }
}

class MusicPlaylist {
    private Song head, tail, current;
    private int size = 0;

    // Add song to the playlist
    public void addSong(String title) {
        Song newSong = new Song(title);
        if (tail == null) {
            head = tail = current = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        size++;
        System.out.println(Colors.GREEN + "Added: " + title +Colors.RESET);
    }

    // Remove song from the playlist
    public void removeSong(String title) {
        if (head == null) {
            System.out.println(Colors.RED + "Playlist is empty." + Colors.RESET);
            return;
        }

        Song temp = head;
        while (temp != null && !temp.title.equals(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println(Colors.RED + "Song not found in the playlist." + Colors.RESET);
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null){
              head.prev = null;
              }
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null){
              tail.next = null;
              }
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        if (temp == current) {
            current = head; // Reset current to the start if the current song is removed
        }

        size--;
        System.out.println(Colors.RED + "Removed: " + title + Colors.RESET);
    }

    // Get playlist size
    public void getSize() {
        System.out.println(Colors.BLUE + "Number of Songs in Playlist: " + size + Colors.RESET);
    }

    // Play the current song
    public void playPlaylist() {
        if (current == null) {
            System.out.println(Colors.RED + "Playlist is empty." + Colors.RESET);
            return;
        }

        Song temp = current;
        while (temp != null) {
            System.out.println("Playing: " + temp.title);
            System.out.println(Colors.YELLOW + "Playing Next Song..." + Colors.RESET);
            temp = temp.next;
        }
        System.out.println(Colors.GREEN + "Playlist finished." + Colors.RESET);
    }

    public void deletePlaylist() {
        head = tail = current = null;
        size = 0;
        System.out.println(Colors.RED + "Playlist Deleted." + Colors.RESET);
    }    

    // Play next song
    public void nextSong() {
        if (current == null) {
            System.out.println(Colors.RED + "Playlist is empty." + Colors.RESET);
            return;
        }
        if (current.next == null) {
            System.out.println(Colors.YELLOW + "You are at the last song." + Colors.RESET);
        } else {
            current = current.next;
            System.out.println(Colors.GREEN + "Playing: " + current.title + Colors.RESET);
        }
    }

    // Play previous song
    public void previousSong() {
        if (current == null) {
        	System.out.println(Colors.RED + "Playlist is empty." + Colors.RESET);
            return;
        }
        if (current.prev == null) {
            System.out.println(Colors.YELLOW + "You are at the first song." + Colors.RESET);
        } else {
            current = current.prev;
            System.out.println(Colors.GREEN + "Playing: " + current.title + Colors.RESET);
        }
    }

    // Display all songs in the playlist
    public void displayPlaylist() {
        if (head == null) {
        	System.out.println(Colors.RED + "Playlist is empty." + Colors.RESET);
            return;
        }
        System.out.println(Colors.BLUE + "Current Playlist:" + Colors.RESET);
        Song temp = head;
    	int i = 1;
        while (temp != null) {
            if (temp == current) {
                System.out.println(Colors.GREEN + i + ". " + temp.title + " (Currently Playing)" + Colors.RESET);
            } else {
                System.out.println(i + ". " + temp.title);
            }
            i++;
            temp = temp.next;
        }
    }
}

public class SpotifyClone {
    public static void main(String[] args) {
        MusicPlaylist my_playlist = new MusicPlaylist();
        Scanner scan = new Scanner(System.in);
        System.out.println(Colors.BLUE + "WELCOME TO SPOTIFY CLONE" + Colors.RESET);
        while (true) {
            System.out.println("\n1. Add Song to Playlist");
            System.out.println("2. Start Playing");
            System.out.println("3. Next Song");
            System.out.println("4. Previous Song");
            System.out.println("5. Display Playlist");
            System.out.println("6. Show Playlist Size");
            System.out.println("7. Remove Song");
            System.out.println("8. Exit");

            System.out.print(Colors.YELLOW + "Choose an option: " + Colors.RESET);
            int choice = scan.nextInt();
            scan.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print(Colors.YELLOW + "Enter song title: " + Colors.RESET);
                    String title = scan.nextLine();
                    my_playlist.addSong(title);
                }
                case 2 -> my_playlist.playPlaylist();
                case 3 -> my_playlist.nextSong();
                case 4 -> my_playlist.previousSong();
                case 5 -> my_playlist.displayPlaylist();
                case 6 -> my_playlist.getSize();
                case 7 -> {
                    System.out.print(Colors.YELLOW + "Enter song title to remove: " + Colors.RESET);
                    String title = scan.nextLine();
                    my_playlist.removeSong(title);
                }
                case 8 -> {
                    System.out.println(Colors.RED + "Exiting... Goodbye!" + Colors.RESET);
                    scan.close();
                    return;
                }
                default -> System.out.println(Colors.RED + "Invalid choice! Try again." + Colors.RESET);
            }
        }
    }
}
