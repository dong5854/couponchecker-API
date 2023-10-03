package com.dong.couponchecker.domain.service;

import com.dong.couponchecker.domain.*;
import com.dong.couponchecker.exception.*;
import com.dong.couponchecker.web.dto.ClubDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final MemberClubRepository memberClubRepository;

    public void createClub(long creatorMemberId, ClubDto clubDto) throws DataNotFoundException  {
        Optional<Member> creator = memberRepository.findById(creatorMemberId);
        if (creator.isEmpty()) throw new DataNotFoundException();
        Club createdClub = new Club(clubDto.getName(), clubDto.getPassword());
        createdClub.addMember(creator.get());
        clubRepository.save(createdClub);
    }

    public void addMember(long memberId, long clubId, String password) throws DataNotFoundException, WrongPasswordException {
        Optional<Club> club = clubRepository.findById(clubId);
        if (club.isEmpty()) throw new DataNotFoundException();
        if (!club.get().getPassword().equals(password)) throw new WrongPasswordException();
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) throw new DataNotFoundException();
        club.get().addMember(member.get());
    }

    public void removeMember(long memberId, long clubId) throws DataNotFoundException {
        Optional<MemberClub> memberClub = memberClubRepository.findById(new MemberClubId(memberId, clubId));
        if (memberClub.isEmpty()) throw new DataNotFoundException();
        memberClub.get().remove();
    }
}
