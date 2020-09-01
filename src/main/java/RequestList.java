import java.util.ArrayList;

public class RequestList {
    private ArrayList<Request> Requests;

    public RequestList() {
        Requests = new ArrayList<Request>();
    }

    public ArrayList<Request> getAllRequests() {
        return Requests;
    }

    public void addRequest(Request t) {
        Requests.add(t);
    }

    public void deleteRequest(int index) throws IndexOutOfBoundsException {
        Requests.remove(index);
    }

    public void markRequestDone(int index) throws IndexOutOfBoundsException {
        Requests.get(index).setDone();
    }

    public int getSize() {
        return Requests.size();
    }

    public Request getRequest(int index) {
        return Requests.get(index);
    }
}