public class VideoListOrdered {

    public VideoNode header;
    public VideoNode footer;
    private int contentIndex;
    private int size;
    private long min = Long.MAX_VALUE;
    private VideoNode closest = null;

    public VideoListOrdered(int index) {
        header = null;
        footer = null;
        contentIndex = index;
    }

    public void add(VideoNode toAdd) {
        /*
        System.out.print("adding: ");
        toAdd.videoContents.print(contentIndex);
        System.out.println();
        */

        if (header == null) {
            header = toAdd;
            footer = toAdd;
        } else {
            addRootless(header, toAdd);
        }
        size = size + 1;
        //System.out.println("size: " + size);
    }

    private void addRootless(VideoNode current, VideoNode toAdd) {
        if (current == null) {
            //System.out.print("\tadded after 1: ");
            //System.out.println();
            return;
        }

        if (toAdd.videoContents.compareTo(current.videoContents, contentIndex) >= 0) {
            addRootless(current.next, toAdd);
            if (current.next == null) {
                current.addNext(toAdd);
                footer = current.next;
                /*
                System.out.print("\tadded after 2: ");
                current.videoContents.print(contentIndex);
                System.out.println();

                System.out.print("header: ");
                header.videoContents.print(contentIndex);
                System.out.println();
                System.out.print("footer: ");
                footer.videoContents.print(contentIndex);
                System.out.println();
                */
            }
        } else { //if (toAdd.videoContents.compareTo(current.videoContents, contentIndex) <= 0) {
            /*
            System.out.print("\tadded before: ");
            current.videoContents.print(contentIndex);
            System.out.println();
            */

            if (current == header) {
                header = toAdd;
                toAdd.next = current;
                current.previous = toAdd;
                //System.out.println("Adding before header");
            } else {
                current.previous.next = toAdd;
                toAdd.next = current;
                toAdd.previous = current.previous;
                current.previous = toAdd;
            }
            return;
        }
    }

    private void insert(VideoNode insertBeforeMe, VideoNode toInsert) {

        System.out.println("\tadded before: ");
        insertBeforeMe.videoContents.print(contentIndex);
        System.out.println();

        toInsert.next = insertBeforeMe;
        toInsert.previous = insertBeforeMe.previous;
        insertBeforeMe.previous = toInsert;
    }


    public void print() {
        traverseListPrint(header);
    }

    private void traverseListPrint(VideoNode n) {
        if (n == null) return;
        else n.videoContents.print(contentIndex);
        System.out.print(", ");
        traverseListPrint(n.next);
    }

    public int getSize() {
        return size;
    }

    public VideoNode getLeftmost(VideoNode node) {
        return header;
    }

    public VideoNode getRightmost(VideoNode node) {
        return footer;
    }

    public VideoNode getClosest(VideoNode find) {
        findClosest(find);
        return closest;
    }

    public void findClosest(VideoNode toFind) {
        if (header == null) {
            return;
        } else {
            findClosestRootless(header, toFind);
        }
    }

    private void findClosestRootless(VideoNode current, VideoNode toFind) {
        /*
        if (current != null) {
            System.out.print("Current: ");
            current.videoContents.print(contentIndex);
            System.out.println();
        }
        if (closest != null) {
            System.out.print("\tClosest: ");
            closest.videoContents.print(contentIndex);
            System.out.println();
        }
         */

        if (current == null) {
            return;
        }
        if (toFind.videoContents.subtract(current.videoContents, contentIndex) < min) {
            min = toFind.videoContents.subtract(current.videoContents, contentIndex);
            closest = current;
            if ((toFind.videoContents.subtract(current.videoContents, contentIndex)) == 0) {
                return;
            }
        }
        findClosestRootless(current.next, toFind);
    }

}

