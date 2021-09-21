package com.nablarch.example.app.web.handler;

import com.nablarch.example.app.web.common.file.TemporaryFileFailedException;
import nablarch.common.dao.NoDataException;
import nablarch.common.web.session.SessionKeyNotFoundException;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpErrorResponse;
import nablarch.fw.web.HttpResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import javax.persistence.OptimisticLockException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

/**
 * {@link ExampleErrorForwardHandler}のテスト。
 */
public class ExampleErrorForwardHandlerTest {

    private ExampleErrorForwardHandler sut = new ExampleErrorForwardHandler();

    @Test
    public void success_shouldReturnHttpResponse() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.addHandler((o, context1) -> new HttpResponse("success.jsp"));

        final HttpResponse actual = (HttpResponse) sut.handle(null, context);
        assertThat(actual.getContentPath().getPath(), is("success.jsp"));
    }

    @Test
    public void sessionKeyNotFound_shouldThrowDoubleSubmissionErrorPage() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.addHandler((o, context1) -> {
            throw new SessionKeyNotFoundException("key");
        });

        Exception exception = assertThrows(HttpErrorResponse.class, () -> sut.handle(new Object(), context));
        assertThat((HttpErrorResponse)exception, is(ContentPathMatcher.hasContentPath("/WEB-INF/view/common/errorPages/doubleSubmissionError.jsp")));
        assertThat((HttpErrorResponse)exception, is(StatusCodeMatcher.isStatusCode(400)));
    }

    @Test
    public void noDataFound_shouldThrowPageNotFoundPage() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.addHandler((o, context1) -> {
            throw new NoDataException();
        });

        Exception exception = assertThrows(HttpErrorResponse.class, () -> sut.handle(new Object(), context));
        assertThat((HttpErrorResponse)exception, is(ContentPathMatcher.hasContentPath("/WEB-INF/view/common/errorPages/pageNotFoundError.jsp")));
        assertThat((HttpErrorResponse)exception, is(StatusCodeMatcher.isStatusCode(404)));
    }

    @Test
    public void optimisticLockException_shouldThrowOptimisticLockErrorPage() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.addHandler((o, context1) -> {
            throw new OptimisticLockException();
        });

        Exception exception = assertThrows(HttpErrorResponse.class, () -> sut.handle(new Object(), context));
        assertThat((HttpErrorResponse)exception, is(ContentPathMatcher.hasContentPath("/WEB-INF/view/common/errorPages/optimisticLockError.jsp")));
        assertThat((HttpErrorResponse)exception, is(StatusCodeMatcher.isStatusCode(400)));

    }

    @Test
    public void failedToTempFile_shouldThrowInternalError() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.addHandler((o, context1) -> {
            throw new TemporaryFileFailedException(new Throwable());
        });

        Exception exception = assertThrows(HttpErrorResponse.class, () -> sut.handle(new Object(), context));
        assertThat((HttpErrorResponse)exception, is(StatusCodeMatcher.isStatusCode(500)));

    }

    private static class ContentPathMatcher extends TypeSafeMatcher<HttpErrorResponse> {

        private final String contentPath;

        public ContentPathMatcher(final String contentPath) {
            this.contentPath = contentPath;
        }

        static ContentPathMatcher hasContentPath(final String content) {
            return new ContentPathMatcher(content);
        }

        @Override
        protected void describeMismatchSafely(final HttpErrorResponse item, final Description mismatchDescription) {
            mismatchDescription.appendText("was ")
                               .appendValue(item.getResponse()
                                                .getContentPath());
        }

        @Override
        protected boolean matchesSafely(final HttpErrorResponse item) {
            return item.getResponse()
                       .getContentPath()
                       .getPath()
                       .equals(contentPath);
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("content path: ")
                       .appendValue(contentPath);
        }
    }

    private static class StatusCodeMatcher extends TypeSafeMatcher<HttpErrorResponse> {

        private final int statusCode;

        public StatusCodeMatcher(final int statusCode) {
            this.statusCode = statusCode;
        }

        static StatusCodeMatcher isStatusCode(int statusCode) {
            return new StatusCodeMatcher(statusCode);
        }

        @Override
        protected void describeMismatchSafely(final HttpErrorResponse item, final Description mismatchDescription) {
            mismatchDescription.appendText("was ")
                               .appendValue(item.getResponse()
                                                .getStatusCode());
        }

        @Override
        protected boolean matchesSafely(final HttpErrorResponse item) {
            return item.getResponse()
                       .getStatusCode() == statusCode;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("status code: ")
                       .appendValue(statusCode);
        }
    }
}