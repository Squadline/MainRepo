package com.keepingatimeline.kat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dana on 5/19/2016.
 * Pretty much everything in this class was copied from online documentation or was automatically
 * generated by Android Studio. -- Dana
 */
public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Event> eventList;
    private Context ctx;

    // saves off the passed eventList, removing null events from it
    public EventAdapter(Context context, ArrayList<Event> eventListParam) {
        this.eventList = eventListParam;
        this.ctx = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolderPhoto extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView photoTitle;
        public TextView photoDate;
        public TextView photoText;
        public ImageView photoPhoto;
        public ViewHolderPhoto(View v) {
            super(v);
            photoTitle = (TextView) v.findViewById(R.id.photo_title);
            photoDate = (TextView) v.findViewById(R.id.photo_date);
            photoText = (TextView) v.findViewById(R.id.photo_text);
            photoPhoto = (ImageView) v.findViewById(R.id.photo_photo);

            Context context = v.getContext();
            Typeface photoTitleFont = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.RobotoRegular));
            photoTitle.setTypeface(photoTitleFont);


            final ImageView editPhotoEvent = (ImageView) v.findViewById(R.id.editPhotoEvent);
            editPhotoEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    PopupMenu editMenu = new PopupMenu(v.getContext(), editPhotoEvent);
                    editMenu.getMenuInflater().inflate(R.menu.event_menu, editMenu.getMenu());

                    editMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            //do stuff
                            return true;
                        }
                    });

                    editMenu.show();
                }
            });
        }
    }

    public static class ViewHolderQuote extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView quoteTitle;
        public TextView quoteDate;
        public TextView quoteText;
        public TextView quoteSpeaker;
        public ImageView editQuote;
        public ViewHolderQuote(View v) {
            super(v);
            quoteTitle = (TextView) v.findViewById(R.id.quote_title);
            quoteDate = (TextView) v.findViewById(R.id.quote_date);
            quoteText = (TextView) v.findViewById(R.id.quote_text);
            quoteSpeaker = (TextView) v.findViewById(R.id.quote_speaker);
            editQuote = (ImageView) v.findViewById(R.id.editQuoteEvent);

            Context context = v.getContext();
            Typeface quoteTitleFont = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.RobotoRegular));
            quoteTitle.setTypeface(quoteTitleFont);

            Typeface quoteTextFont = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.RobotoSlabRegular));
            quoteText.setTypeface(quoteTextFont);

            Typeface quoteSpeakerFont = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.RobotoMedium));
            quoteSpeaker.setTypeface(quoteSpeakerFont);


            final ImageView editQuoteEvent = (ImageView) v.findViewById(R.id.editQuoteEvent);
            editQuoteEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    PopupMenu editMenu = new PopupMenu(v.getContext(), editQuoteEvent);
                    editMenu.getMenuInflater().inflate(R.menu.event_menu, editMenu.getMenu());

                    editMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            //do stuff
                            return true;
                        }
                    });

                    editMenu.show();
                }
            });
        }
    }

    public static class ViewHolderText extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textTitle;
        public TextView textDate;
        public TextView textText;
        public ViewHolderText(View v) {
            super(v);
            textTitle = (TextView) v.findViewById(R.id.text_title);
            textDate = (TextView) v.findViewById(R.id.text_date);
            textText = (TextView) v.findViewById(R.id.text_text);

            Context context = v.getContext();
            Typeface textTitleFont = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.RobotoRegular));
            textTitle.setTypeface(textTitleFont);


            final ImageView editTextEvent = (ImageView) v.findViewById(R.id.editTextEvent);
            editTextEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v){

                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.getMenuInflater().inflate(R.menu.event_settings_menu, popup.getMenu());
                    popup.show();

                    popup.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Lol").setMessage("Hope this works");
                            builder.create().show();
                            return true;
                        }
                    });

                    popup.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Delete Event").setMessage("Delete This Event?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create().show();
                            return true;
                        }
                    });

                }
            });
        }
    }

    @Override
    public int getItemViewType(int position){
        switch(eventList.get(position).getType()) {
            case "photo":
                return 0;
            case "quote":
                return 1;
            case "text":
            default:
                return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        RecyclerView.ViewHolder view;
        switch(viewType) {
            case 0:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_event_layout, parent, false);
                view = new ViewHolderPhoto(itemView);
                break;
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_event_layout, parent, false);
                view = new ViewHolderQuote(itemView);
                break;
            case 2:
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_event_layout, parent, false);
                view = new ViewHolderText(itemView);
                break;
        }

        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Event event = eventList.get(position);
        switch(event.getType()) {
            case "photo":
                ((ViewHolderPhoto)holder).photoTitle.setText(event.getTitle());
                ((ViewHolderPhoto)holder).photoDate.setText(event.getDate());
                ((ViewHolderPhoto)holder).photoText.setText(event.getString1());
                ((ViewHolderPhoto)holder).photoPhoto.setImageBitmap(BitmapCache.getBitmapFromMemCache(event.getString2()));
                break;
            case "quote":

                SpannableString fancyQuoteText = new SpannableString("\"" + event.getString1() + "\"");
                Drawable leftQuotation = ctx.getDrawable(R.drawable.quotation_left);
                Drawable rightQuotation = ctx.getDrawable(R.drawable.quotation_right);

                leftQuotation.setBounds(0, 0, leftQuotation.getIntrinsicWidth(), leftQuotation.getIntrinsicHeight());
                rightQuotation.setBounds(0, 0, rightQuotation.getIntrinsicWidth(), rightQuotation.getIntrinsicHeight());

                ImageSpan leftSpan = new ImageSpan(leftQuotation, ImageSpan.ALIGN_BASELINE);
                ImageSpan rightSpan = new ImageSpan(rightQuotation, ImageSpan.ALIGN_BASELINE);
                fancyQuoteText.setSpan(leftSpan, 0, 1, 0);
                fancyQuoteText.setSpan(rightSpan, (fancyQuoteText.length() - 1), fancyQuoteText.length(), 0);

                ((ViewHolderQuote)holder).quoteTitle.setText(event.getTitle());
                ((ViewHolderQuote)holder).quoteDate.setText(event.getDate());
                ((ViewHolderQuote)holder).quoteText.setText(fancyQuoteText);
                ((ViewHolderQuote)holder).quoteSpeaker.setText("–" + event.getString2());

                break;
            case "text":
            default:
                ((ViewHolderText)holder).textTitle.setText(event.getTitle());
                ((ViewHolderText)holder).textDate.setText(event.getDate());
                ((ViewHolderText)holder).textText.setText(event.getString1());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
