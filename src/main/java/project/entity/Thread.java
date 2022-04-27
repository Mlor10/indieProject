package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a thread
 */
@Entity(name = "Thread")
@Table(name = "thread")
public class Thread {
    @Column(name = "thread_title")
    private String threadTitle;
    @Column(name = "thread_content")
    private String threadContent;
    @Column(name = "thread_views")
    private int threadViews;
    @Column(name = "thread_replies")
    private int threadReplies;
    @Column(name = "thread_date")
    private LocalDate threadDate;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Reply> replies = new HashSet<>();

    /**
     * Instantiates a new thread
     */
    public Thread() {
    }

    /**
     * Constructor for a new thread
     * @param threadTitle thread title
     * @param threadContent thread content
     * @param threadViews thread views
     * @param threadReplies thread replies
     * @param user thread's user
     */
    public Thread(String threadTitle, String threadContent, int threadViews, int threadReplies, LocalDate threadDate, User user) {
        this.threadTitle = threadTitle;
        this.threadContent = threadContent;
        this.threadViews = threadViews;
        this.threadReplies = threadReplies;
        this.threadDate = threadDate;
        this.user = user;
    }

    /**
     * Gets thread title
     *
     * @return the card name
     */
    public String getThreadTitle() {
        return threadTitle;
    }

    /**
     * Sets thread title
     *
     * @param threadTitle the card name
     */
    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    /**
     * Gets thread content
     *
     * @return the thread content
     */
    public String getThreadContent() {
        return threadContent;
    }

    /**
     * Sets thread content
     *
     * @param threadContent thread content
     */
    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    /**
     * Gets thread views
     *
     * @return the thread views
     */
    public int getThreadViews() {
        return threadViews;
    }

    /**
     * Sets thread views
     *
     * @param threadViews thread views
     */
    public void setThreadViews(int threadViews) {
        this.threadViews = threadViews;
    }

    /**
     * Gets thread replies
     *
     * @return
     */
    public int getThreadReplies() {
        return threadReplies;
    }

    /**
     * Sets thread replies
     *
     * @param threadReplies thread replies
     */
    public void setThreadReplies(int threadReplies) {
        this.threadReplies = threadReplies;
    }

    /**
     * Gets thread date
     *
     * @return thread date
     */
    public LocalDate getThreadDate() {
        return threadDate;
    }

    /**
     * Sets the thread date
     *
     * @param threadDate thread date
     */
    public void setThreadDate(LocalDate threadDate) {
        this.threadDate = threadDate;
    }

    /**
     * Gets id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets replies
     *
     * @return the replies
     */
    public Set<Reply> getReplies() {
        return replies;
    }

    /**
     * Sets replies
     *
     * @param replies the replies
     */
    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    /**
     * Add reply
     *
     * @param reply the reply
     */
    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setThread(this);
    }

    /**
     * Remove reply
     *
     * @param reply the reply
     */
    public void removeReply(Reply reply) {
        replies.remove(reply);
        reply.setThread(null);
    }

    @Override
    public String toString() {
        return "Thread{" +
                "threadTitle='" + threadTitle + '\'' +
                ", threadContent='" + threadContent + '\'' +
                ", threadViews=" + threadViews +
                ", threadReplies=" + threadReplies +
                ", threadDate=" + threadDate +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return threadViews == thread.threadViews
                && threadReplies == thread.threadReplies
                && id == thread.id
                && threadTitle.equals(thread.threadTitle)
                && threadContent.equals(thread.threadContent)
                && threadDate.equals(thread.threadDate)
                && user.equals(thread.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadTitle, threadContent, threadViews, threadReplies, threadDate, id, user);
    }
}
