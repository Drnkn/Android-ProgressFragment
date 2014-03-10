package ru.vang.progressswitcher;

import android.view.View;
import android.view.View.OnClickListener;

public interface Switcher {

    /**
     * Return content view or null if the content view has not been initialized.
     *
     * @return content view or null
     * @see #addContentView(android.view.View)
     * @see #addContentView(int)
     * @see #setContentView(android.view.View)
     * @see #setContentView(int)
     */
    public View getContentView();

    /**
     * Add the content content from a layout resource.
     *
     * @param layoutResId resource Id to be inflated.
     * @see #addContentView(android.view.View)
     * @see #setContentView(android.view.View)
     * @see #setContentView(int)
     * @see #getContentView()
     */
    public void addContentView(int layoutResId);

    /**
     * Add the content view to an explicit view. If the content view was
     * installed earlier, the content will be replaced with a new view.
     *
     * @param view the desired content to display, can't be null.
     * @see #addContentView(int)
     * @see #setContentView(int)
     * @see #setContentView(android.view.View)
     * @see #getContentView()
     */
    public void addContentView(View view);

    /**
     * If content view was included in layout it can be set explicitly by Id.
     *
     * @param contentViewId Resource Id to be set.
     * @see #addContentView(android.view.View)
     * @see #addContentView(int)
     * @see #setContentView(int)
     * @see #getContentView()
     */
    public void setContentView(int contentViewId);

    /**
     * If content view was included in layout it can be set explicitly.
     *
     * @param contentView The desired view to set. Value can't be null.
     * @see #addContentView(android.view.View)
     * @see #addContentView(int)
     * @see #setContentView(int)
     * @see #getContentView()
     */
    public void setContentView(View contentView);

    /**
     * Display content view if it's not already shown.
     *
     * @see #showContent(boolean)
     * @see #showProgress()
     * @see #showEmpty()
     * @see #showError()
     */
    public void showContent();

    /**
     * Display content view if it's not already shown.
     *
     * @param animate If true, the view will be shown with animation, false
     *                otherwise.
     * @see #showContent()
     * @see #showProgress()
     * @see #showEmpty()
     * @see #showError()
     */
    public void showContent(boolean animate);

    /**
     * Display progress view if it's not already shown.
     *
     * @see #showProgress(boolean)
     * @see #showContent()
     * @see #showEmpty()
     * @see #showError()
     */
    public void showProgress();

    /**
     * Display progress view if it's not already shown.
     *
     * @param animate If true, the view will be shown with animation, false
     *                otherwise.
     * @see #showContent()
     * @see #showProgress()
     * @see #showEmpty()
     * @see #showError()
     */
    public void showProgress(boolean animate);

    /**
     * Display empty view if it's not already shown and corresponding view is
     * provided.
     *
     * @see #showEmpty(boolean)
     * @see #showContent()
     * @see #showProgress()
     * @see #showError()
     */
    public void showEmpty();

    /**
     * Display empty view if it's not already shown and corresponding view is
     * provided.
     *
     * @param animate If true, the view will be shown with animation, false
     *                otherwise.
     * @see #showEmpty()
     * @see #showContent()
     * @see #showProgress()
     * @see #showError()
     */
    public void showEmpty(boolean animate);

    /**
     * Display error view if it's not already shown and corresponding view is
     * provided.
     *
     * @see #showError(boolean)
     * @see #showContent()
     * @see #showProgress()
     * @see #showEmpty()
     */
    public void showError();

    /**
     * Display error view if it's not already shown and corresponding view is
     * provided.
     *
     * @param animate If true, the view will be shown with animation, false
     *                otherwise.
     * @see #showError()
     * @see #showContent()
     * @see #showProgress()
     * @see #showEmpty()
     */
    public void showError(boolean animate);

    /**
     * The default content for a ProgressSwitcher has a TextView that can be
     * shown when the content is empty({@link #showEmpty()}). If you would like
     * to have it shown, call this method to supply the text it should use.
     *
     * @param resId Identification of string from a resources
     * @see #setEmptyText(CharSequence)
     * @see #setEmptyText(CharSequence, int)
     * @see #setEmptyText(int, int)
     */
    public void setEmptyText(int resId);

    /**
     * The default content for a ProgressSwitcher has a TextView that can be
     * shown when the content is empty({@link #showEmpty()}). If you would like
     * to have it shown, call this method to supply the text it should use.
     *
     * @param text Text for empty view
     * @see #setEmptyText(int)
     * @see #setEmptyText(CharSequence, int)
     * @see #setEmptyText(int, int)
     */
    public void setEmptyText(CharSequence text);

    /**
     * If custom layout is provided for empty view, you can set empty text to
     * specific view in provided layout.
     *
     * @param resId  Identification of string from a resources
     * @param viewId View id to which text shall be assigned
     * @see #setEmptyText(int)
     * @see #setEmptyText(CharSequence)
     * @see #setEmptyText(CharSequence, int)
     */
    public void setEmptyText(int resId, int viewId);

    /**
     * If custom layout is provided for empty view, you can set empty text to
     * specific view in provided layout.
     *
     * @param text   Text for empty view
     * @param viewId View id to which text shall be assigned
     * @see #setEmptyText(int)
     * @see #setEmptyText(CharSequence)
     * @see #setEmptyText(int, int)
     */
    public void setEmptyText(CharSequence text, int viewId);

    /**
     * The default content for a ProgressSwitcher has a TextView that can be
     * shown when the error occurred ({@link #showError()}). If you would like
     * to change text, call this method to supply the text it should use.
     *
     * @param resId Identification of string from a resources
     * @see #setErrorText(CharSequence)
     * @see #setErrorText(CharSequence, int)
     * @see #setErrorText(int, int)
     */
    public void setErrorText(int resId);

    /**
     * The default content for a ProgressSwitcher has a TextView that can be
     * shown when the error occurred ({@link #showError()}). If you would like
     * to change text, call this method to supply the text it should use.
     *
     * @param text Text for empty view
     * @see #setErrorText(int)
     * @see #setErrorText(CharSequence, int)
     * @see #setErrorText(int, int)
     */
    public void setErrorText(CharSequence text);

    /**
     * If custom layout is provided for error view, you can set empty text to
     * specific view in provided layout.
     *
     * @param resId  Identification of string from a resources
     * @param viewId View id to which text shall be assigned
     * @see #setErrorText(int)
     * @see #setErrorText(CharSequence)
     * @see #setErrorText(CharSequence, int)
     */
    public void setErrorText(int resId, int viewId);

    /**
     * If custom layout is provided for error view, you can set empty text to
     * specific view in provided layout.
     *
     * @param text   Text for empty view
     * @param viewId View id to which text shall be assigned
     * @see #setErrorText(int)
     * @see #setErrorText(CharSequence)
     * @see #setErrorText(int, int)
     */
    public void setErrorText(CharSequence text, int viewId);

    /**
     * If you want provide some action by clicking on empty view, you can set
     * listener here.
     *
     * @param onClickListener On error view click listener
     * @see #setOnErrorViewClickListener(android.view.View.OnClickListener, int)
     */
    public void setOnEmptyViewClickListener(OnClickListener onClickListener);

    /**
     * If you want provide some action by clicking on specific view in empty
     * layout, you can set listener here.
     *
     * @param onClickListener On error view click listener
     * @param viewId          View id to which listener shall be assigned
     * @see #setOnErrorViewClickListener(android.view.View.OnClickListener)
     */
    public void setOnEmptyViewClickListener(OnClickListener onClickListener, int viewId);

    /**
     * If you want provide some action by clicking on error view, you can set
     * listener here.
     *
     * @param onClickListener On error view click listener
     * @see #setOnErrorViewClickListener(android.view.View.OnClickListener, int)
     */
    public void setOnErrorViewClickListener(OnClickListener onClickListener);

    /**
     * If you want provide some action by clicking on specific view in error
     * layout, you can set listener here. You can use {@link ru.vang.progressswitcher.R.id#retry
     * R.id.retry} to set listener to "Retry" button in default layout.
     *
     * @param onClickListener On error view click listener
     * @param viewId          View id to which listener shall be assigned
     * @see #setOnErrorViewClickListener(android.view.View.OnClickListener)
     */
    public void setOnErrorViewClickListener(OnClickListener onClickListener, int viewId);

    /**
     * Returns true if the progress view is displayed.
     *
     * @return a boolean indicating if the progress view is displayed.
     * @see #isContentDisplayed()
     * @see #isEmptyViewDisplayed()
     * @see #isErrorViewDisplayed()
     */
    public boolean isProgressDisplayed();

    /**
     * Returns true if the content view is displayed.
     *
     * @return a boolean indicating if the content view is displayed.
     * @see #isProgressDisplayed()
     * @see #isEmptyViewDisplayed()
     * @see #isErrorViewDisplayed()
     */
    public boolean isContentDisplayed();

    /**
     * Returns true if the empty view is displayed.
     *
     * @return a boolean indicating if the empty view is displayed.
     * @see #isProgressDisplayed()
     * @see #isContentDisplayed()
     * @see #isErrorViewDisplayed()
     */
    public boolean isEmptyViewDisplayed();

    /**
     * Returns true if the error view is displayed.
     *
     * @return a boolean indicating if the error view is displayed.
     * @see #isProgressDisplayed()
     * @see #isContentDisplayed()
     * @see #isEmptyViewDisplayed()
     */
    public boolean isErrorViewDisplayed();

    /**
     * Set custom animation for both appearing view and disappearing view.
     *
     * @param animationIn  Animation for appearing view
     * @param animationOut Animation for disappearing view
     */
    public void setCustomAnimation(final int animationIn, final int animationOut);

}
