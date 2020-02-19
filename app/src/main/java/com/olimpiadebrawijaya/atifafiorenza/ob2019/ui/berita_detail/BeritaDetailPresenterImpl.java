package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.berita_detail;

public class BeritaDetailPresenterImpl implements BeritaDetailPresenter {
    private BeritaDetailView mBeritaDetailView;


    public BeritaDetailPresenterImpl(BeritaDetailView beritaDetailView) {
        this.mBeritaDetailView = beritaDetailView;
    }

    @Override
    public void attemptGetDataFromPreviousActivity() {
        mBeritaDetailView.updateUI();
    }

}
